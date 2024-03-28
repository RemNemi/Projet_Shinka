package com.example.Projet_Shinka.Profile

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.Projet_Shinka.R
class ProfileFragment : Fragment() {

    // Création de la vue du fragment
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.profile_fragment, container, false)

        // Configuration de ViewPager2 et TabLayout
        val viewPager = view.findViewById<ViewPager2>(R.id.profile_viewpager)
        val tabLayout = view.findViewById<TabLayout>(R.id.profile_tabs)
        viewPager.adapter = SectionsPagerAdapter(this)

        // Liaison de TabLayout avec ViewPager2
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = getTabTitle(position)
            tab.view.contentDescription = "Tab ${tab.text}"
        }.attach()

        return view
    }

    // Gestion du clic sur le bouton d'édition du nom
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonEditName = view.findViewById<Button>(R.id.button_edit_name)
        val textViewName = view.findViewById<TextView>(R.id.profile_name)

        buttonEditName.setOnClickListener {
            showEditNameDialog(textViewName)
        }
    }

    private fun getTabTitle(position: Int): String? = when (position) {
        0 -> "Progress"
        1 -> "Skills"
        2 -> "Badges"
        else -> null
    }

    private fun showEditNameDialog(textView: TextView) {
        val editText = EditText(context)
        AlertDialog.Builder(context)
            .setTitle("Edit Name")
            .setView(editText)
            .setPositiveButton("OK") { dialog, _ -> textView.text = editText.text.toString(); dialog.dismiss() }
            .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, _ -> dialog.cancel() })
            .create()
            .show()
    }
}

class SectionsPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount() = 3 // Nombre d'onglets

    // Création des fragments pour chaque onglet
    override fun createFragment(position: Int): Fragment = when (position) {
        0 -> ProgressFragment()
        1 -> SkillsFragment()
        2 -> BadgesFragment()
        else -> throw IllegalStateException("Position invalide")
    }
}
