package com.example.Projet_Shinka

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        navView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_hub -> {
                    // Remplacez avec votre logique pour sélectionner le fragment HUB
                    replaceFragment(HubFragment())
                    true
                }
                R.id.navigation_entrainement -> {
                    // Remplacez avec votre logique pour sélectionner le fragment Entrainement
                    replaceFragment(EntrainementFragment())
                    true
                }
                R.id.navigation_my_ai -> {
                    // Remplacez avec votre logique pour sélectionner le fragment IA
                    replaceFragment(MyAIFragment())
                    true
                }
                R.id.navigation_profile -> {
                    // Remplacez avec votre logique pour sélectionner le fragment Profile
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
            .replace(R.id.fragment_container, fragment) // Remplacez fragment_container par votre ID de FrameLayout où les fragments sont chargés
            .commit()
    }
}

class ProfileFragment : Fragment() {
    // ...
}
