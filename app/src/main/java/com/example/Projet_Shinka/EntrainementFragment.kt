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
        // Ici, on se contente d'infalter le layout du fragment
        return inflater.inflate(R.layout.fragment_entrainement, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Ici, vous pouvez accéder à votre vue car elle est déjà créée
        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view_entrainement)
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Créer et définir l'adapter avec des données
        // Remplacez /* Ajoutez ici vos données */ par vos données réelles
        recyclerView.adapter = EntrainementAdapter(listOf(/* Ajoutez ici vos données */))
    }
}
