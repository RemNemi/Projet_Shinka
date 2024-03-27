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

// Assurez-vous d'importer les classes nécessaires pour la réponse...

class MyAIFragment : Fragment() {

    private lateinit var aiAssistant: OpenAIClient

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Initialisez aiAssistant ici avec votre clé API ou d'autres paramètres
        aiAssistant = OpenAIClient(/* clé API et autres paramètres */)
        // Inflatez votre layout de MyAIFragment ici et retournez la vue
        return inflater.inflate(R.layout.fragment_my_ai, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialisation des éléments d'interface utilisateur
        val userInputField: EditText = view.findViewById(R.id.userInput)
        val aiResponseView: TextView = view.findViewById(R.id.aiResponse)
        val sendButton: Button = view.findViewById(R.id.sendButton)

        sendButton.setOnClickListener {
            val userPrompt = userInputField.text.toString()
            if (userPrompt.isNotBlank()) {
                aiAssistant.sendPrompt(userPrompt) { response ->
                    activity?.runOnUiThread {
                        when (response) {
                            is OpenAIResponse.Success -> aiResponseView.text = response.data
                            is OpenAIResponse.Error -> aiResponseView.text = response.message
                        }
                    }
                }
            } else {
                aiResponseView.text = getString(R.string.prompt_empty_error)
            }
        }
    }
}
    // Assurez-vous que la classe scellée Response est définie correctement en dehors de MyAIFragment et est accessible
    sealed class Response<out T> {
        data class Success<out T>(val data: T) : Response<T>()
        data class Error(val message: String) : Response<Nothing>()
    }
