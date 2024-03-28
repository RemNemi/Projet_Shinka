package com.example.Projet_Shinka.Task

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.Projet_Shinka.R

class TaskAdapter(
    private val tasks: List<Task>,
    private val onDeleteClick: (Task) -> Unit,
    private val onEditClick: (Task) -> Unit
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    // ViewHolder contenant les éléments UI pour une tâche
    class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewTitle: TextView = view.findViewById(R.id.textViewTitle)
        val editTaskButton: ImageButton = view.findViewById(R.id.editTaskButton)
        val deleteTaskButton: ImageButton = view.findViewById(R.id.deleteTaskButton)
    }

    // Création et initialisation du ViewHolder pour chaque tâche
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(view)
    }

    // Association des données de tâche aux éléments UI et gestion des clics
    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]
        holder.textViewTitle.text = task.title
        holder.editTaskButton.setOnClickListener { onEditClick(task) }
        holder.deleteTaskButton.setOnClickListener { onDeleteClick(task) }
    }

    // Retourne le nombre total de tâches
    override fun getItemCount() = tasks.size
}
