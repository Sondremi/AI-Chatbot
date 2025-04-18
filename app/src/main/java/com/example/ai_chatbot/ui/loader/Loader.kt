package com.example.ai_chatbot.ui.loader

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Loader() {
    LinearProgressIndicator(
        modifier = Modifier
            .fillMaxWidth(0.4f)
            .padding(horizontal = 8.dp)
            .height(2.dp),
        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.7f)
    )
}
