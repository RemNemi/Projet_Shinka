package com.example.Projet_Shinka.Entrainement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.Projet_Shinka.R

class EntrainementFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_entrainement, container, false)

        // Configuration des boutons pour les différents types d'entrainements physiques
        view.findViewById<Button>(R.id.boutonPhysique1).setOnClickListener { ouvrirEntrainementPhysique("Force") }
        view.findViewById<Button>(R.id.boutonPhysique2).setOnClickListener { ouvrirEntrainementPhysique("Endurance") }
        view.findViewById<Button>(R.id.boutonPhysique3).setOnClickListener { ouvrirEntrainementPhysique("Agilité") }
        view.findViewById<Button>(R.id.boutonPhysique4).setOnClickListener { ouvrirEntrainementPhysique("Etirement") }

        // Configuration des boutons pour les entrainements mentaux
        view.findViewById<Button>(R.id.boutonMental1).setOnClickListener { ouvrirEntrainementMental("Méditation") }
        view.findViewById<Button>(R.id.boutonMental2).setOnClickListener { ouvrirEntrainementMental("Respiration") }
        view.findViewById<Button>(R.id.boutonMental3).setOnClickListener { ouvrirEntrainementMental("Challenges") }
        view.findViewById<Button>(R.id.boutonMental4).setOnClickListener { ouvrirEntrainementMental("Affirmations") }

        // Configuration des boutons pour les entrainements intellectuels
        view.findViewById<Button>(R.id.boutonIntellect1).setOnClickListener { ouvrirEntrainementIntellect("Concentration") }
        view.findViewById<Button>(R.id.boutonIntellect2).setOnClickListener { ouvrirEntrainementIntellect("Journal") }
        view.findViewById<Button>(R.id.boutonIntellect3).setOnClickListener { ouvrirEntrainementIntellect("Tests") }
        view.findViewById<Button>(R.id.boutonIntellect4).setOnClickListener { ouvrirEntrainementIntellect("Cours") }


        return view
    }
    // Ouvre le fragment correspondant
    private fun ouvrirEntrainementPhysique(nomEntrainement: String) {
        val fragment = EntrainementPhysiqueFragment().apply {
            arguments = Bundle().apply {
                putString("nomEntrainement", nomEntrainement)
            }
        }
        fragmentManager?.beginTransaction()
            ?.replace(R.id.fragment_container, fragment)
            ?.addToBackStack(null)
            ?.commit()
    }
    // Ouvre le fragment correspondant
    private fun ouvrirEntrainementMental(nomEntrainement: String) {
        val fragment = EntrainementMentalFragment().apply {
            arguments = Bundle().apply {
                putString("nomEntrainement", nomEntrainement)
            }
        }
    }
    // Ouvre le fragment correspondant
    private fun ouvrirEntrainementIntellect(nomEntrainement: String) {
        val fragment = EntrainementIntellectFragment().apply {
            arguments = Bundle().apply {
                putString("nomEntrainement", nomEntrainement)
            }
        }
    }

}
