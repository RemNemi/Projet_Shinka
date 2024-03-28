package com.example.Projet_Shinka.Profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.Projet_Shinka.databinding.FragmentSkillsBinding

class SkillsFragment : Fragment() {
    // Gestion du View Binding pour l'interface du fragment
    private var _binding: FragmentSkillsBinding? = null
    private val binding get() = _binding!!

    // Crée la vue du fragment et initialise le View Binding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSkillsBinding.inflate(inflater, container, false)
        return binding.root
    }

    // Nettoie le binding lors de la destruction de la vue
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
