package com.example.Projet_Shinka

import android.telecom.Call
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.MediaType
import java.io.IOException



class OpenAIClient {

    private val client = OkHttpClient()
    private val apiKey = "Cle a mettre"// CléAPI

    fun sendPrompt(prompt: String, completion: (String) -> Unit) {
        val body = RequestBody.create(
            MediaType.parse("application/json; charset=utf-8"),
            "{\"prompt\":\"$prompt\", \"max_tokens\":150}"
        )
        val request = Request.Builder()
            .url("https://api.openai.com/v1/engines/davinci-codex/completions")
            .post(body)
            .addHeader("Authorization", "Bearer $apiKey")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
                completion("Une erreur s'est produite lors de la communication avec OpenAI.")
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) throw IOException("Réponse inattendue $response")

                    completion(response.body()?.string() ?: "")
                }
            }
        })
    }
}
