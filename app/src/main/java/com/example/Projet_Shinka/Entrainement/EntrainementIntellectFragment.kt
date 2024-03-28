package com.example.Projet_Shinka.Entrainement

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.Projet_Shinka.R

class EntrainementIntellectFragment : Fragment() {
    // Crée la vue du fragment, affiche le nom de l'entraînement
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_entrainement_intellect, container, false)
        val nomEntrainement = arguments?.getString("nomEntrainement") ?: ""
        val textViewTitle = view.findViewById<TextView>(R.id.textViewEntrainementTitle)
        textViewTitle.text = nomEntrainement
        // Personnalisez l'affichage pour le type d'entraînement intellect ici
        return view
    }
}
