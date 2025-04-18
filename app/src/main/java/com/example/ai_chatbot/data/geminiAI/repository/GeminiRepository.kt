package com.example.ai_chatbot.data.geminiAI.repository

import com.example.ai_chatbot.data.geminiAI.datasource.GeminiDatasource
import com.example.ai_chatbot.data.geminiAI.models.GeminiResponse

class GeminiRepository {
    private val datasource = GeminiDatasource()

    suspend fun getGeminiResponse(prompt: String): GeminiResponse {
        return datasource.getGeminiResponse(prompt)
    }
}