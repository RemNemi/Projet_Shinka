package com.example.Projet_Shinka.My_IA

import android.util.Log
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException

class OpenAIClient {

    private val client = OkHttpClient()
    private val apiKey = "sk-..." // Utilisez un mécanisme sécurisé pour stocker et récupérer la clé API

    fun sendPrompt(prompt: String, completion: (OpenAIResponse<String>) -> Unit) {
        val mediaType = "application/json; charset=utf-8".toMediaType()
        val body = "{\"prompt\":\"$prompt\", \"max_tokens\":150}".toRequestBody(mediaType)
        val request = Request.Builder()
            .url("https://api.openai.com/v1/engines/davinci-codex/completions")
            .post(body)
            .addHeader("Authorization", "Bearer $apiKey")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("OpenAIClient", "Erreur de réseau: ${e.message}")
                completion(OpenAIResponse.Error("Erreur de réseau ou problème de serveur."))
            }
            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) {
                        Log.e("OpenAIClient", "Erreur de réponse: ${response.message}")
                        completion(OpenAIResponse.Error("Réponse inattendue: ${response.message}"))
                    } else {
                        response.body?.string()?.let { responseBody ->
                            completion(OpenAIResponse.Success(responseBody))
                        } ?: completion(OpenAIResponse.Error("Réponse vide de la part d'OpenAI."))
                    }
                }
            }
        })
    }
}

sealed class OpenAIResponse<out T> {
    data class Success<out T>(val data: T) : OpenAIResponse<T>()
    data class Error(val message: String) : OpenAIResponse<Nothing>()
}
