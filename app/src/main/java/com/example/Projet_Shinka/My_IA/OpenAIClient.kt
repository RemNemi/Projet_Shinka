// Package et importations nécessaires.
package com.example.Projet_Shinka.My_IA

import android.util.Log
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.Request
import okhttp3.Response
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException



// Classe pour interagir avec l'API OpenAI.
class OpenAIClient {
    private var client = OkHttpClient() // Client HTTP pour les requêtes.
    private val apiKey = "MaCléAPIOpenIA" // Clé API OpenAI.

    init {
        // Configuration de l'intercepteur de journalisation pour les requêtes.
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        // Ajout de l'intercepteur au client HTTP.
        client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    // Envoie une invite à OpenAI et reçoit une réponse.
    fun sendPrompt(prompt: String, completion: (OpenAIResponse<String>) -> Unit) {
        // Formatage du corps de la requête en JSON.
        val mediaType = "application/json; charset=utf-8".toMediaType()
        val body = """
        {
            "model": "gpt-3.5-turbo-0125",
            "messages": [
                {"role": "system", "content": 
                "tu est un assistant en devloppement personnel, tu cherche a aider en peu de mot une personne qui te pose une question."},
                {"role": "user", "content": "$prompt"}
            ]
        }
        """.toRequestBody(mediaType)

        // Création et configuration de la requête HTTP.
        val request = Request.Builder()
            .url("https://api.openai.com/v1/chat/completions")
            .post(body)
            .addHeader("Authorization", "Bearer $apiKey")
            .build()

        // Envoi de la requête de manière asynchrone.
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                // Log en cas d'échec réseau et transmission de l'erreur.
                Log.e("OpenAIClient", "Erreur de réseau: ${e.message}")
                completion(OpenAIResponse.Error("Erreur de réseau ou problème de serveur."))
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) {
                        // Log en cas de réponse non réussie et transmission de l'erreur.
                        Log.e("OpenAIClient", "Erreur de réponse: ${response.message}")
                        completion(OpenAIResponse.Error("Réponse inattendue: ${response.message}"))
                    } else {
                        // Succès : extraction et transmission des données.
                        response.body?.string()?.let { responseBody ->
                            completion(OpenAIResponse.Success(responseBody))
                        } ?: completion(OpenAIResponse.Error("Réponse vide de la part d'OpenAI."))
                    }
                }
            }
        })
    }
}

// Réponse encapsulée de l'API OpenAI, succès ou erreur.
sealed class OpenAIResponse<out T> {
    data class Success<out T>(val data: T) : OpenAIResponse<T>()
    data class Error(val message: String) : OpenAIResponse<Nothing>()
}