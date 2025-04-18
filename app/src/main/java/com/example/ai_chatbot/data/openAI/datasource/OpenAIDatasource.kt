package com.example.ai_chatbot.data.openAI.datasource

import com.example.ai_chatbot.data.openAI.models.OpenAIRequest
import com.example.ai_chatbot.data.openAI.models.OpenAIResponse
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.converter.gson.GsonConverterFactory

interface OpenAIDatasource {
    @POST("v1/chat/completions")
    suspend fun getCompletionSample(
        @Header("Authorization") auth: String,
        @Body request: OpenAIRequest
    ): OpenAIResponse

    companion object {
        private const val BASE_URL = "https://api.openai.com/"

        fun create(): OpenAIDatasource {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(OpenAIDatasource::class.java)
        }
    }
}