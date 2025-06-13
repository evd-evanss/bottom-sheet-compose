package com.sugarspoon.bottom.sheet.components

import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Button(
    modifier: Modifier = Modifier, // Um comentário desnecessário
    text: String,
    onClick: () -> Unit,
) // Removi essa linha de propósito
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = MaterialTheme.shapes.small,
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
        )
    }
}