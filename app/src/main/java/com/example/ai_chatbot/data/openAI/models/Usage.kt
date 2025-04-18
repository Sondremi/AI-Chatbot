package com.example.ai_chatbot.data.openAI.models

data class Usage(
    val prompt_tokens: Int,
    val completion_tokens: Int,
    val total_tokens: Int
)