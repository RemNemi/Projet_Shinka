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


//c2stSDFic0RHUGt0bERHNktvelRESE5UM0JsYmtGSm8wVERFYVJxSWNBWk5JdXBseW1H
class OpenAIClient {
    // Le client OkHttp pour effectuer les requêtes HTTP.
    private var client = OkHttpClient()

    // La clé API d'OpenAI. Il est important de la garder sécurisée et non exposée.
    private val apiKey = ""

    init {
        // Création et configuration de l'intercepteur de journalisation pour déboguer les requêtes.
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        // Construction du client OkHttp avec l'intercepteur de journalisation.
        client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    // Méthode pour envoyer une invite (prompt) à OpenAI et recevoir une réponse.
    fun sendPrompt(prompt: String, completion: (OpenAIResponse<String>) -> Unit) {
        // Préparation des données de la requête au format JSON.
        val mediaType = "application/json; charset=utf-8".toMediaType()
        val body = """
        {
            "model": "gpt-3.5-turbo-0125",
            "messages": [
                {"role": "system", "content": "You are a helpful assistant."},
                {"role": "user", "content": "$prompt"}
            ]
        }
        """.toRequestBody(mediaType)

        // Création de la requête HTTP.
        val request = Request.Builder()
            .url("https://api.openai.com/v1/chat/completions")
            .post(body)
            .addHeader("Authorization", "Bearer $apiKey")
            .build()

        // Envoi de la requête de manière asynchrone.
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                // Gestion des erreurs réseau et journalisation pour le débogage.
                Log.e("OpenAIClient", "Erreur de réseau: ${e.message}")
                completion(OpenAIResponse.Error("Erreur de réseau ou problème de serveur."))
            }
            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) {
                        // Gestion des réponses non réussies de l'API.
                        Log.e("OpenAIClient", "Erreur de réponse: ${response.message}")
                        completion(OpenAIResponse.Error("Réponse inattendue: ${response.message}"))
                    } else {
                        // Traitement de la réponse en cas de succès.
                        response.body?.string()?.let { responseBody ->
                            completion(OpenAIResponse.Success(responseBody))
                        } ?: completion(OpenAIResponse.Error("Réponse vide de la part d'OpenAI."))
                    }
                }
            }
        })
    }
}

// Classe pour encapsuler les réponses de l'API OpenAI, qu'elles soient réussies ou non.
sealed class OpenAIResponse<out T> {
    data class Success<out T>(val data: T) : OpenAIResponse<T>()
    data class Error(val message: String) : OpenAIResponse<Nothing>()
}
