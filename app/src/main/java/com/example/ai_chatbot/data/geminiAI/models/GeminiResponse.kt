package com.example.ai_chatbot.data.geminiAI.models

sealed class GeminiResponse {
    data class Success(val text: String) : GeminiResponse()
    data class Error(val message: String) : GeminiResponse()
}