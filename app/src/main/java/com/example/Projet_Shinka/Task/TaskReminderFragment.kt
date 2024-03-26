package com.example.Projet_Shinka

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.Projet_Shinka.Task.TaskAdapter
import com.example.Projet_Shinka.Task.TaskManager
import kotlinx.coroutines.launch

class TaskReminderFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var taskAdapter: TaskAdapter
    private lateinit var taskManager: TaskManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_task_reminder, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.tasksRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)

        taskManager = TaskManager(requireContext())

        loadTasks()
    }

    private fun loadTasks() {
        lifecycleScope.launch {
            taskAdapter = TaskAdapter(taskManager.getTasks())
            recyclerView.adapter = taskAdapter
        }
    }
}
