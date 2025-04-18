package com.example.ai_chatbot.data.openAI.models

data class OpenAIRequest(
    val model: String = "gpt-3.5-turbo",
    val messages: List<Message>,
    val max_tokens: Int = 150,
    val temperature: Double = 0.7,
    val top_p: Double = 1.0,
    val n: Int = 1,
    val stop: List<String>? = null,
    val presence_penalty: Double = 0.0,
    val frequency_penalty: Double = 0.0
)