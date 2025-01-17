package com.sugarspoon.bottom.sheet.sample

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sugarspoon.bottom.sheet.ui.theme.AppTheme

@Composable
fun SampleScreen() {
    Scaffold {
        Column(
            modifier = Modifier.padding(it)
        ) {
            ListItem(
                headlineContent = {

                    Text(text = "Sample")
                }
            )
        }
    }
}

@Preview
@Composable
fun SampleScreenPreview() {
    AppTheme {
        SampleScreen()
    }
}