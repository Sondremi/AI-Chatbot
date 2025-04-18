package com.example.ai_chatbot.ui.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ai_chatbot.data.openAI.repository.OpenAIRepository
import com.example.ai_chatbot.ui.chatbotScreen.ChatbotMessage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class OpenAIViewModel: ViewModel() {
    private val openAIRepository = OpenAIRepository()

    private val _openAIUIState = MutableStateFlow(OpenAIUIState())
    val openAIUIState: StateFlow<OpenAIUIState> = _openAIUIState

    private val _conversationHistory = mutableStateListOf<ChatbotMessage>()
    val conversationHistory: List<ChatbotMessage> get() = _conversationHistory

    fun addUserMessage(message: String) {
        _conversationHistory.add(ChatbotMessage(message, true))
    }

    fun addBotMessage(message: String) {
        _conversationHistory.add(ChatbotMessage(message, false))
    }

    fun getCompletionsSamples(prompt: String) {
        viewModelScope.launch {
            _openAIUIState.update {
                it.copy(isLoading = true)
            }
            try {
                val responseSample = openAIRepository.getCompletionSample(prompt)

                _openAIUIState.update {
                    it.copy(response = responseSample)
                }

                addBotMessage(responseSample)
            } catch (e: Exception) {
                val error = "Error: ${e.message}"

                _openAIUIState.update {
                    it.copy(response = error)
                }
            } finally {
                _openAIUIState.update {
                    it.copy(isLoading = false)
                }
            }
        }
    }
}

data class OpenAIUIState(
    val response: String = "",
    val isLoading: Boolean = false,
    val isStreaming: Boolean = false
)
