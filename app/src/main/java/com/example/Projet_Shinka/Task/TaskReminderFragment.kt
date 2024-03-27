package com.example.Projet_Shinka

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.Projet_Shinka.Task.Task
import com.example.Projet_Shinka.Task.TaskAdapter
import com.example.Projet_Shinka.Task.TaskManager
import com.example.Projet_Shinka.Task.TaskType
import com.example.Projet_Shinka.Task.TimeCategory
import kotlinx.coroutines.launch

class TaskReminderFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var taskAdapter: TaskAdapter
    private lateinit var taskManager: TaskManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_task_reminder, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.tasksRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        taskManager = TaskManager(requireContext())
        loadTasks()

        val editTextTaskTitle: EditText = view.findViewById(R.id.editTextTaskTitle)
        val addTaskButton: Button = view.findViewById(R.id.addTaskButton)

        addTaskButton.setOnClickListener {
            val taskTitle = editTextTaskTitle.text.toString()
            if (taskTitle.isNotEmpty()) {
                addNewTask(taskTitle)
                editTextTaskTitle.text.clear()
            } else {
                Toast.makeText(context, "Le titre de la tâche ne peut pas être vide", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun addNewTask(title: String) {
        val newTask = Task(title = title, type = TaskType.SPORT, timeCategory = TimeCategory.DAILY)
        lifecycleScope.launch {
            taskManager.addTask(newTask)
            loadTasks()
        }
    }

    private fun loadTasks() {
        lifecycleScope.launch {
            taskAdapter = TaskAdapter(
                taskManager.getTasks(),
                onDeleteClick = { task -> deleteTask(task) },
                onEditClick = { task -> editTask(task) }
            )
            recyclerView.adapter = taskAdapter
        }
    }


    private fun deleteTask(task: Task) {
        lifecycleScope.launch {
            taskManager.deleteTask(task)  // Supprime la tâche de la base de données
            loadTasks()  // Recharge la liste des tâches pour mettre à jour l'affichage
        }
    }




    private fun editTask(task: Task) {
        // Lancez une activité ou un dialogue de modification ici
        // Par exemple, vous pouvez utiliser un AlertDialog avec un champ de saisie pour modifier le titre

        val editText = EditText(context)
        editText.setText(task.title)

        AlertDialog.Builder(context)
            .setTitle("Modifier la tâche")
            .setView(editText)
            .setPositiveButton("Sauvegarder") { _, _ ->
                val newTitle = editText.text.toString()
                if(newTitle.isNotEmpty()) {
                    task.title = newTitle  // Met à jour le titre de la tâche
                    updateTask(task)  // Met à jour la tâche dans la base de données
                }
            }
            .setNegativeButton("Annuler", null)
            .show()
    }

    private fun updateTask(task: Task) {
        lifecycleScope.launch {
            taskManager.updateTask(task)  // Met à jour la tâche dans la base de données
            loadTasks()  // Recharge les tâches pour mettre à jour l'affichage
        }
    }

}
