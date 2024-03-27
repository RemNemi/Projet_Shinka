package com.example.Projet_Shinka

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.Projet_Shinka.Task.TaskManager

class HubFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflatez le layout pour ce fragment
        return inflater.inflate(R.layout.fragment_hub, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Ajoutez TaskReminderFragment au conteneur
        if (savedInstanceState == null) { // Pour éviter de recréer le fragment lors des changements de configuration comme les rotations de l'écran
            childFragmentManager.beginTransaction()
                .replace(R.id.taskReminderContainer, TaskReminderFragment())
                .commit()
        }
    }
}




/* ancien HubFragment : si l'on souhaite changer pour plusieur option
class HubFragment : Fragment() {
    // Gestionnaire de tâches (assurez-vous que cette classe existe dans votre projet)
    private lateinit var taskManager: TaskManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflatez le layout pour ce fragment
        return inflater.inflate(R.layout.fragment_hub, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialiser le gestionnaire de tâches avec un contexte non-null
        taskManager = TaskManager(requireContext())

        // Configurer les boutons ou les éléments d'interface utilisateur ici
        val buttonTaskReminder: Button = view.findViewById(R.id.button_task_reminder)
        buttonTaskReminder.setOnClickListener {
            navigateToTaskReminder()
        }

        val buttonCalendar: Button = view.findViewById(R.id.button_calendar)
        buttonCalendar.setOnClickListener {
            navigateToCalendar()
        }

        // Ajoutez d'autres boutons et fonctionnalités selon vos besoins...
    }

    private fun navigateToTaskReminder() {
        val taskReminderFragment = TaskReminderFragment()
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, taskReminderFragment)
            .addToBackStack(null)
            .commit()
    }

    private fun navigateToCalendar() {
        // Implémentez la navigation vers le calendrier des tâches
        val calendarFragment = CalendarFragment()
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, calendarFragment)
            .addToBackStack(null)
            .commit()
    }


    // Autres fonctions comme la gestion des notifications de rappel des tâches...
    // Assurez-vous que les classes TaskReminderFragment et CalendarFragment existent
}

// Vous devrez créer une classe TaskManager pour gérer les tâches et les notifications
*/