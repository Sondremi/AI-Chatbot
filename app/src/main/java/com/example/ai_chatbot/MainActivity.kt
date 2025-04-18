package com.example.ai_chatbot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.ai_chatbot.ui.chatbotScreen.ChatbotScreen
import com.example.ai_chatbot.ui.theme.AIChatbotTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AIChatbotTheme {
                ChatbotScreen()
            }
        }
    }
}
