package com.example.Projet_Shinka


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

class TaskManager {
    private val tasks = mutableListOf<Task>()

    fun addTask(title: String, type: TaskType, timeCategory: TimeCategory) {
        tasks.add(Task(title = title, type = type, timeCategory = timeCategory))
    }

    // Autres fonctions pour gérer les tâches (suppression, modification, etc.)
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

