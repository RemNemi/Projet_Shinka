package com.example.Projet_Shinka


import android.telecom.Call
import okhttp3.*
import java.io.IOException

class OpenAIClient {

    private val client = OkHttpClient()
    private val apiKey = "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" // Remplacez par le mécanisme de stockage sécurisé approprié

    fun sendPrompt(prompt: String, completion: (Response<String>) -> Unit) {
        val body = RequestBody.create(
            MediaType.parse("application/json; charset=utf-8"),
            "{\"prompt\":\"$prompt\", \"max_tokens\":150}"
        )
        val request = Request.Builder()
            .url("https://api.openai.com/v1/engines/davinci-codex/completions")
            .post(body)
            .addHeader("Authorization", "Bearer $apiKey")
            .build()

        client.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onFailure(call: Call, e: IOException) {
                completion(Response.Error("Erreur de réseau ou problème de serveur."))
            }

            override fun onResponse(call: Call, response: okhttp3.Response) {
                response.use {
                    if (!response.isSuccessful) {
                        completion(Response.Error("Réponse inattendue: ${response.message()}"))
                    } else {
                        response.body()?.string()?.let {
                            completion(Response.Success(it))
                        } ?: completion(Response.Error("Réponse vide de la part d'OpenAI."))
                    }
                }
            }
        })
    }
}

sealed class Response<out T> {
    data class Success<out T>(val data: T) : Response<T>()
    data class Error(val message: String) : Response<Nothing>()
}
