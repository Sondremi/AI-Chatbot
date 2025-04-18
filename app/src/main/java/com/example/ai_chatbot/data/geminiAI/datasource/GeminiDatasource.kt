package com.example.ai_chatbot.data.geminiAI.datasource

import com.example.ai_chatbot.BuildConfig
import com.example.ai_chatbot.data.geminiAI.models.GeminiResponse
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.GenerateContentResponse
import com.google.ai.client.generativeai.type.TextPart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GeminiDatasource {
    private val generativeModel = GenerativeModel(
        modelName = "gemini-2.0-flash",
        apiKey = BuildConfig.GEMINI_API_KEY
    )

    suspend fun getGeminiResponse(prompt: String): GeminiResponse {
        return try {
            val response: GenerateContentResponse = withContext(Dispatchers.IO) {
                generativeModel.generateContent(prompt)
            }

            val textPart = response.candidates.firstOrNull()?.content?.parts?.firstOrNull()
            if (textPart is TextPart) {
                GeminiResponse.Success(textPart.text)
            } else {
                GeminiResponse.Error("Response was not in expected text format")
            }
        } catch (e: Exception) {
            GeminiResponse.Error("Error: ${e.message}")
        }
    }
}