package com.example.Projet_Shinka

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.Projet_Shinka.Entrainement.EntrainementFragment
import com.example.Projet_Shinka.My_IA.MyAIFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        navView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_hub -> {
                    // Logique pour sélectionner le fragment HUB
                    replaceFragment(HubFragment())
                    true
                }
                R.id.navigation_entrainement -> {
                    // Logique pour sélectionner le fragment Entrainement
                    replaceFragment(EntrainementFragment())
                    true
                }
                R.id.navigation_my_ai -> {
                    // Logique pour sélectionner le fragment IA
                    replaceFragment(MyAIFragment())
                    true
                }
                R.id.navigation_profile -> {
                    // Logique pour sélectionner le fragment Profile
                    replaceFragment(ProfileFragment())
                    true
                }
                else -> false
            }
        }

        // Sélectionnez le fragment initial ici si nécessaire, par exemple le HUB
        navView.selectedItemId = R.id.navigation_hub
    }

    // Fonction utilitaire pour remplacer le fragment actuel par le nouveau
    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}

class ProfileFragment : Fragment() {
    // ...
}
