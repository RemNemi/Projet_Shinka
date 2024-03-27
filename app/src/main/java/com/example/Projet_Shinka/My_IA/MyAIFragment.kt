// Importation des bibliothèques nécessaires.
package com.example.Projet_Shinka.My_IA

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.Projet_Shinka.R
import org.json.JSONObject

class MyAIFragment : Fragment() {

    // Déclaration de l'instance d'OpenAIClient. Elle sera initialisée plus tard.
    private lateinit var aiAssistant: OpenAIClient

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // On crée l'assistant AI avec les paramètres nécessaires, y compris la clé API.
        aiAssistant = OpenAIClient(/* clé API et autres paramètres */)

        // On gonfle le layout pour ce fragment et on le retourne pour l'affichage.
        return inflater.inflate(R.layout.fragment_my_ai, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // On récupère les références des éléments d'interface utilisateur.
        val userInputField: EditText = view.findViewById(R.id.userInput)
        val aiResponseView: TextView = view.findViewById(R.id.aiResponse)
        val sendButton: Button = view.findViewById(R.id.sendButton)

        // On définit un écouteur d'événements pour le bouton d'envoi.
        sendButton.setOnClickListener {
            // On récupère le texte saisi par l'utilisateur.
            val userPrompt = userInputField.text.toString()

            // On vérifie si le texte n'est pas vide.
            if (userPrompt.isNotBlank()) {
                // On envoie le texte à l'assistant AI et on gère la réponse.
                aiAssistant.sendPrompt(userPrompt) { response ->
                    activity?.runOnUiThread {
                        when (response) {
                            // En cas de succès, on analyse la réponse JSON et on affiche le contenu.
                            is OpenAIResponse.Success -> {
                                val jsonResponse = JSONObject(response.data)
                                val choices = jsonResponse.getJSONArray("choices")
                                if (choices.length() > 0) {
                                    val firstChoice = choices.getJSONObject(0)
                                    val message = firstChoice.getJSONObject("message")
                                    val content = message.getString("content")
                                    aiResponseView.text = content
                                }
                            }
                            // En cas d'erreur, on affiche le message d'erreur.
                            is OpenAIResponse.Error -> aiResponseView.text = response.message
                        }
                    }
                }
            } else {
                // Si le texte est vide, on affiche un message d'erreur.
                aiResponseView.text = getString(R.string.prompt_empty_error)
            }
        }
    }
}

// Définition de la classe scellée Response pour gérer les réponses de l'API.
sealed class Response<out T> {
    data class Success<out T>(val data: T) : Response<T>()
    data class Error(val message: String) : Response<Nothing>()
}
