// Début du fichier HubFragment.kt
package com.example.Projet_Shinka

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

// Importations nécessaires...

class HubFragment : Fragment() {
    // Gestionnaire de tâches et de notifications
    private lateinit var taskManager: TaskManager

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_hub, container, false)

        // Initialiser le gestionnaire de tâches avec un contexte non-null
        taskManager = TaskManager(requireContext())

        // Configurer les boutons ou les éléments d'interface utilisateur ici...
        val buttonTaskReminder: Button = view.findViewById(R.id.button_task_reminder)
        buttonTaskReminder.setOnClickListener {
            // Afficher les tâches à faire et gérer les rappels
            navigateToTaskReminder()
        }

        val buttonCalendar: Button = view.findViewById(R.id.button_calendar)
        buttonCalendar.setOnClickListener {
            // Afficher le calendrier avec les jours de rappel de tâches
            navigateToCalendar()
        }

        // Ajoutez d'autres boutons et fonctionnalités selon vos besoins...

        return view
    }

    private fun navigateToTaskReminder() {
        // Implémentez la navigation vers la page de rappel des tâches
        val taskReminderFragment =
            TaskReminderFragment() // Utilisation du constructeur pour créer une instance
        fragmentManager?.beginTransaction()?.replace(R.id.fragment_container, taskReminderFragment)
            ?.addToBackStack(null)?.commit()
    }

    private fun navigateToCalendar() {
        // Implémentez la navigation vers le calendrier des tâches
        // Ajoutez ici le code pour naviguer vers le fragment du calendrier ou l'activité
    }

    // Autres fonctions comme la gestion des notifications de rappel des tâches...

}



// Vous devrez créer une classe TaskManager pour gérer les tâches et les notifications
