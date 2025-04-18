package com.example.ai_chatbot.data.openAI.models

data class Choice(
    val index: Int,
    val message: Message,
    val finish_reason: String
)