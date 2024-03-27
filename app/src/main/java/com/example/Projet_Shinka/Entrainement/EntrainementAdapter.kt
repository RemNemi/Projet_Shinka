package com.example.Projet_Shinka.Entrainement

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.Projet_Shinka.R

class EntrainementAdapter(private val entrainements: List<Entrainement>) : RecyclerView.Adapter<EntrainementAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewNom: TextView = view.findViewById(R.id.textView_entrainement)
        val textViewDescription: TextView = view.findViewById(R.id.textViewDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_entrainement, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val entrainement = entrainements[position]
        holder.textViewNom.text = entrainement.nom
        holder.textViewDescription.text = entrainement.description
    }

    override fun getItemCount() = entrainements.size
}
