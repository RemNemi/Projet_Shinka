package com.example.Projet_Shinka.Profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.Projet_Shinka.databinding.FragmentProgressBinding

class ProgressFragment : Fragment() {
    // View Binding pour une gestion facile de la vue du fragment
    private var _binding: FragmentProgressBinding? = null
    private val binding get() = _binding!!

    // Inflate le layout du fragment avec View Binding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentProgressBinding.inflate(inflater, container, false)
        return binding.root
    }

    // Nettoie le binding lors de la destruction de la vue pour éviter les fuites de mémoire
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
