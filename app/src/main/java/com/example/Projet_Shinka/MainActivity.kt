package com.example.Projet_Shinka

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.Projet_Shinka.Entrainement.EntrainementFragment
import com.example.Projet_Shinka.My_IA.MyAIFragment
import com.example.Projet_Shinka.Profile.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        // Gère les clics sur les éléments de la BottomNavigationView
        navView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_hub -> {
                    // Affiche le fragment HUB
                    replaceFragment(HubFragment())
                    true
                }
                R.id.navigation_entrainement -> {
                    // Affiche le fragment Entrainement
                    replaceFragment(EntrainementFragment())
                    true
                }
                R.id.navigation_my_ai -> {
                    // Affiche le fragment IA
                    replaceFragment(MyAIFragment())
                    true
                }
                R.id.navigation_profile -> {
                    // Affiche le fragment Profile
                    replaceFragment(ProfileFragment())
                    true
                }
                else -> false
            }
        }

        // Sélectionne le fragment initial (HUB)
        navView.selectedItemId = R.id.navigation_hub
    }

    // Remplace le fragment actuellement affiché par un nouveau
    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}

