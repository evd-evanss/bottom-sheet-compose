package com.sugarspoon.bottom.sheet.sample

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import com.sugarspoon.bottom.sheet.components.Button
import com.sugarspoon.bottom.sheet.components.TopBar

@Composable
fun SampleBottomSheet(
    isExpanded: Boolean,
    onDismiss: () -> Unit,
) {
    BottomSheet(
        modifier = Modifier,
        paddingValues = PaddingValues(16.dp),
        isExpanded = isExpanded,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        onDismiss = onDismiss,
    ) {
        TopBar(
            title = "Title session",
            onClick = onDismiss,
        )

        Text(
            text = LoremIpsum(30).values.first()
        )

        Button(
            modifier = Modifier.fillMaxWidth(),
            text = "Close",
            onClick = onDismiss,
        )
    }
}