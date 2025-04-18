package com.example.ai_chatbot.data.openAI.repository

import com.example.ai_chatbot.BuildConfig
import com.example.ai_chatbot.data.openAI.datasource.OpenAIDatasource
import com.example.ai_chatbot.data.openAI.models.Message
import com.example.ai_chatbot.data.openAI.models.OpenAIRequest

class OpenAIRepository() {
    private val openAIDatasource = OpenAIDatasource.create()

    suspend fun getCompletionSample(question: String): String {
        return try {
            val request = OpenAIRequest(messages = listOf(Message(content = question, role = "user")))
            val response = openAIDatasource.getCompletionSample("Bearer ${BuildConfig.OPENAI_API_KEY}", request)
            response.choices.firstOrNull()?.message?.content ?: "No response"
        } catch (e: Exception) {
            "Error: ${e.message}"
        }
    }
}