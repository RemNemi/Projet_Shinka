package com.example.Projet_Shinka.My_IA

import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException

class OpenAIClient {

    private val client = OkHttpClient()
    private val apiKey = "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" // Utilisez un mécanisme sécurisé pour stocker et récupérer la clé API

    fun sendPrompt(prompt: String, completion: (Response<String>) -> Unit) {
        val mediaType = "application/json; charset=utf-8".toMediaType() // Correction ici
        val body = "{\"prompt\":\"$prompt\", \"max_tokens\":150}".toRequestBody(mediaType) // Correction ici
        val request = Request.Builder()
            .url("https://api.openai.com/v1/engines/davinci-codex/completions")
            .post(body)
            .addHeader("Authorization", "Bearer $apiKey")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {
                completion(OpenAIResponse.Error("Erreur de réseau ou problème de serveur."))
            }

            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                response.use {
                    if (!response.isSuccessful) {
                        completion(OpenAIResponse.Error("Réponse inattendue: ${response.message}")) // Correction ici
                    } else {
                        response.body?.string()?.let {
                            completion(OpenAIResponse.Success(it))
                        } ?: completion(OpenAIResponse.Error("Réponse vide de la part d'OpenAI."))
                    }
                }
            }
        })
    }
}

sealed class OpenAIResponse<out T> {
    data class Success<out T>(val data: T) : Response<T>()
    data class Error(val message: String) : Response<Nothing>()
}
