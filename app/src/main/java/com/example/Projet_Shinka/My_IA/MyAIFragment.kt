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

    private lateinit var aiAssistant: OpenAIClient // Instance de l'assistant AI

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        aiAssistant = OpenAIClient(/* clé API et autres paramètres */) // Initialisation de l'assistant AI
        return inflater.inflate(R.layout.fragment_my_ai, container, false) // Chargement du layout
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userInputField: EditText = view.findViewById(R.id.userInput)
        val aiResponseView: TextView = view.findViewById(R.id.aiResponse)
        val sendButton: Button = view.findViewById(R.id.sendButton)

        sendButton.setOnClickListener {
            val userPrompt = userInputField.text.toString()
            if (userPrompt.isNotBlank()) {
                aiAssistant.sendPrompt(userPrompt) { response ->
                    activity?.runOnUiThread {
                        when (response) {
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
                            is OpenAIResponse.Error -> {
                                aiResponseView.text = response.message
                            }
                        }
                    }
                }
            } else {
                aiResponseView.text = getString(R.string.prompt_empty_error)
            }
        }
    }
}


