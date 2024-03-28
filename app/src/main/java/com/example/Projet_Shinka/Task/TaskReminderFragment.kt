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

    // Variables pour la gestion de la vue et des données
    private lateinit var recyclerView: RecyclerView
    private lateinit var taskAdapter: TaskAdapter
    private lateinit var taskManager: TaskManager

    // Crée la vue du fragment
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate la mise en page du fragment_task_reminder
        return inflater.inflate(R.layout.fragment_task_reminder, container, false)
    }

    // Configure la vue une fois qu'elle est créée
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configuration du RecyclerView pour l'affichage des tâches
        recyclerView = view.findViewById(R.id.tasksRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Initialisation de TaskManager pour la gestion des données
        taskManager = TaskManager(requireContext())

        // Charge les tâches existantes dans l'interface utilisateur
        loadTasks()

        // Gestion de l'ajout de nouvelles tâches
        val editTextTaskTitle: EditText = view.findViewById(R.id.editTextTaskTitle)
        val addTaskButton: Button = view.findViewById(R.id.addTaskButton)
        addTaskButton.setOnClickListener {
            val taskTitle = editTextTaskTitle.text.toString()
            if (taskTitle.isNotEmpty()) {
                addNewTask(taskTitle)  // Ajoute la tâche et nettoie le champ de saisie
                editTextTaskTitle.text.clear()
            } else {
                // Affiche un message si le champ de saisie est vide
                Toast.makeText(context, "Le titre de la tâche ne peut pas être vide", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Ajoute une nouvelle tâche dans la base de données et rafraîchit l'affichage
    private fun addNewTask(title: String) {
        val newTask = Task(title = title, type = TaskType.SPORT, timeCategory = TimeCategory.DAILY)
        lifecycleScope.launch {
            taskManager.addTask(newTask)
            loadTasks()  // Recharge les tâches pour afficher la nouvelle tâche
        }
    }

    // Charge les tâches depuis la base de données et les affiche dans le RecyclerView
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

    // Supprime une tâche sélectionnée et rafraîchit l'affichage
    private fun deleteTask(task: Task) {
        lifecycleScope.launch {
            taskManager.deleteTask(task)
            loadTasks()  // Mise à jour de l'interface utilisateur après suppression
        }
    }

    // Affiche un dialogue pour modifier une tâche et sauvegarde les modifications
    private fun editTask(task: Task) {
        val editText = EditText(context).apply { setText(task.title) }
        AlertDialog.Builder(context)
            .setTitle("Modifier la tâche")
            .setView(editText)
            .setPositiveButton("Sauvegarder") { _, _ ->
                val newTitle = editText.text.toString()
                if(newTitle.isNotEmpty()) {
                    task.title = newTitle  // Mise à jour du titre de la tâche
                    updateTask(task)  // Mise à jour de la tâche dans la base de données
                }
            }
            .setNegativeButton("Annuler", null)
            .show()
    }

    // Met à jour la tâche modifiée dans la base de données et rafraîchit l'affichage
    private fun updateTask(task: Task) {
        lifecycleScope.launch {
            taskManager.updateTask(task)
            loadTasks()  // Recharge les tâches pour afficher les modifications
        }
    }
}
