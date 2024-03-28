package com.example.Projet_Shinka.Entrainement

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.Projet_Shinka.R

class EntrainementPhysiqueFragment : Fragment() {
    // Crée la vue du fragment, affiche le nom de l'entraînement
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_entrainement_physique, container, false)

        // Récupération du nom de l'entraînement depuis les arguments
        val nomEntrainement = arguments?.getString("nomEntrainement") ?: "Entrainement par défaut"

        // Recherche du TextView dans la vue et mise à jour de son contenu
        val textViewTitle = view.findViewById<TextView>(R.id.textViewEntrainementTitle)
        textViewTitle.text = nomEntrainement

        // Autres personnalisations ici

        return view
    }
}
