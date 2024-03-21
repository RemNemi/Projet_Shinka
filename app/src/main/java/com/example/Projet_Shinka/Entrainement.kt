package com.example.Projet_Shinka

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment

class Entrainement : Fragment() {

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_entrainement, container, false)

        // Ajoutez un OnClickListener pour chaque élément du menu Entrainement
        view.findViewById<LinearLayout>(R.id.layout_note).setOnClickListener {
            navigateToNote()
        }

        // Répétez pour les autres fonctionnalités...

        return view
    }

    // Méthode pour naviguer vers la fonctionnalité Bloc-note
    private fun navigateToNote() {
        // Implémentez la navigation vers la fonctionnalité Bloc-note
    }

    // Ajoutez des méthodes similaires pour la respiration, la méditation, etc.
}
