package com.example.Projet_Shinka


import android.content.Context
import com.google.gson.Gson
import java.util.*

data class Task(val id: UUID = UUID.randomUUID(),
                var title: String,
                var type: TaskType,
                var timeCategory: TimeCategory)

enum class TaskType {
    SPORT, KNOWLEDGE, WELLBEING, HELP
}

enum class TimeCategory {
    DAILY, WEEKLY, EXCEPTIONAL
}

class TaskManager(private val context: Context) {
    private val tasks = mutableListOf<Task>()

    fun addTask(title: String, type: TaskType, timeCategory: TimeCategory) {
        tasks.add(Task(title = title, type = type, timeCategory = timeCategory))
    }

    fun getTasks(): List<Task> = tasks

    fun getTasksByType(type: TaskType): List<Task> = tasks.filter { it.type == type }

    fun getTasksByTimeCategory(timeCategory: TimeCategory): List<Task> = tasks.filter { it.timeCategory == timeCategory }

    fun removeTask(taskId: UUID) {
        tasks.removeAll { it.id == taskId }
    }

    fun updateTask(taskId: UUID, newTitle: String, newType: TaskType, newTimeCategory: TimeCategory) {
        tasks.find { it.id == taskId }?.apply {
            title = newTitle
            type = newType
            timeCategory = newTimeCategory
        }
    }

    fun saveTasks() {
        val sharedPreferences = context.getSharedPreferences("TaskManager", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val tasksJson = gson.toJson(tasks)
        editor.putString("tasks", tasksJson)
        editor.apply()
    }

    fun loadTasks() {
        val sharedPreferences = context.getSharedPreferences("TaskManager", Context.MODE_PRIVATE)
        val tasksJson = sharedPreferences.getString("tasks", null)
        tasksJson?.let {
            val gson = Gson()
            val type = object : com.google.gson.reflect.TypeToken<List<Task>>() {}.type
            val loadedTasks: List<Task> = gson.fromJson(it, type)
            tasks.clear()
            tasks.addAll(loadedTasks)
        }
    }
}




































/*
// Classe TaskManager
class TaskManager(private val context: Context) {
    // Liste pour stocker les tâches
    private val tasks = mutableListOf<Task>()

    // Fonction pour ajouter une tâche
    fun addTask(task: Task) {
        tasks.add(task)
        // Ajoutez la logique pour planifier des notifications si nécessaire
    }

    // Fonction pour supprimer une tâche
    fun removeTask(task: Task) {
        tasks.remove(task)
        // Annulez la notification si elle a été planifiée
    }

    // Fonction pour obtenir toutes les tâches
    fun getAllTasks(): List<Task> {
        return tasks
    }

    // Fonction pour obtenir les tâches d'un jour spécifique
    fun getTasksForDate(date: LocalDate): List<Task> {
        // Filtrez et retournez les tâches qui ont des rappels pour la date spécifiée
        return tasks.filter { task ->
            task.reminderDate == date
        }
    }

    // Fonction pour planifier des rappels/notification pour les tâches
    private fun scheduleReminder(task: Task) {
        // Implémentez la logique de programmation de rappel/notification ici
    }

    // Classe interne pour définir une tâche
    class Task(val name: String, val description: String, val reminderDate: LocalDate) {
        // Ajoutez d'autres propriétés et fonctions selon vos besoins...
    }
}
*/

