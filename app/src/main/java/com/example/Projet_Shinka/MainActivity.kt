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
private fun createNotificationChannel() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val name = getString(R.string.channel_name)
        val descriptionText = getString(R.string.channel_description)
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
            description = descriptionText
        }
        val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}


class HubFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_hub, container, false)

        // Configurez les actions des boutons ici, par exemple :
        val buttonCategory1: Button = view.findViewById(R.id.button_category1)
        buttonCategory1.setOnClickListener {
            // Action lorsque le bouton 1 est cliqué
        }

        // Configurations supplémentaires pour d'autres boutons...

        return view
    }
}



// Vous devrez créer une classe EntrainementAdapter pour gérer l'affichage des éléments de la liste


class MyAIFragment : Fragment() {
    // ...
}

class ProfileFragment : Fragment() {
    // ...
}
