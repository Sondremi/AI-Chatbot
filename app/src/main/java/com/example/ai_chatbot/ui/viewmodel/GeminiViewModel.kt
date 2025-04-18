package com.example.ai_chatbot.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ai_chatbot.data.geminiAI.models.GeminiResponse
import com.example.ai_chatbot.data.geminiAI.repository.GeminiRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GeminiViewModel : ViewModel() {
    private val geminiRepository = GeminiRepository()

    private val _geminiUIState = MutableStateFlow(GeminiUIState())
    val geminiUIState: StateFlow<GeminiUIState> = _geminiUIState

    fun getGeminiResponse(prompt: String) {
        viewModelScope.launch {
            _geminiUIState.update {
                it.copy(isLoading = true)
            }
            when (val response = geminiRepository.getGeminiResponse(prompt)) {
                is GeminiResponse.Success -> {
                    _geminiUIState.update {
                        it.copy(response = response.text, isLoading = false)
                    }
                }
                is GeminiResponse.Error -> {
                    _geminiUIState.update {
                        it.copy(response = response.message, isLoading = false)
                    }
                }
            }
        }
    }
}

data class GeminiUIState(
    val response: String = "",
    val isLoading: Boolean = false
)