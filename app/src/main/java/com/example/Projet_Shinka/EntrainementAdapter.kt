package com.example.Projet_Shinka

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EntrainementAdapter(private val entrainements: List<Entrainement<Any?>>) : RecyclerView.Adapter<EntrainementAdapter.ViewHolder>() {

    // Vous devrez créer une classe de données Entrainement pour représenter chaque activité

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // Récupérer et définir les vues que vous voulez utiliser dans chaque élément, par exemple:
        val textView: TextView = view.findViewById(R.id.textView_entrainement)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Créer une nouvelle vue pour chaque élément
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_entrainement, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Configurer le contenu de chaque élément de la liste
        val entrainement = entrainements[position]
        holder.textView.text = entrainement.nom
        // Plus de configuration si nécessaire
    }

    override fun getItemCount() = entrainements.size
}
