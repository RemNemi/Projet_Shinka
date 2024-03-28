package com.example.Projet_Shinka.Profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.Projet_Shinka.R

class BadgesFragment : Fragment() {

    // Crée la vue du fragment Badges
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate le layout du fragment Badges
        return inflater.inflate(R.layout.fragment_badges, container, false)
    }

    // Logique supplémentaire une fois la vue créée
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Ajoutez votre code ici si nécessaire
    }
}
