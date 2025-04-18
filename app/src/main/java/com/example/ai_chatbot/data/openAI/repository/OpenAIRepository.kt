package com.example.ai_chatbot.data.openAI.repository

import com.example.ai_chatbot.BuildConfig
import com.example.ai_chatbot.data.openAI.datasource.OpenAIDatasource
import com.example.ai_chatbot.data.openAI.models.Message
import com.example.ai_chatbot.data.openAI.models.OpenAIRequest

class OpenAIRepository {
    private val openAIDatasource = OpenAIDatasource.create()

    suspend fun getCompletionSample(prompt: String): String {
        return try {
            val request = OpenAIRequest(messages = listOf(Message(content = prompt, role = "user")))
            val response = openAIDatasource.getCompletionSample("Bearer ${BuildConfig.OPENAI_API_KEY}", request)
            response.choices.firstOrNull()?.message?.content ?: "No response"
        } catch (e: Exception) {
            if (e.message?.contains("429") == true) {
                "Rate limit exceeded. Please try again later."
            } else {
                "Error: ${e.message}"
            }
        }
    }
}