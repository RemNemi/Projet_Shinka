package com.example.Projet_Shinka

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.Projet_Shinka.EntrainementAdapter
import com.example.Projet_Shinka.R

class EntrainementFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_entrainement, container, false)
    }
/*
        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view_entrainement)
        // Assurez-vous de créer et de configurer un Adapter pour votre RecyclerView
        // Par exemple, EntrainementAdapter qui prend une liste d'activités

        // Configurer le layout manager
        recyclerView.layoutManager = LinearLayoutManager(context)
        // Créer et définir l'adapter avec des données
        recyclerView.adapter = EntrainementAdapter(listOf(/* Ajoutez ici vos données */))

        return view
    */
}