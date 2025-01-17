package com.sugarspoon.bottom.sheets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
internal fun BottomSheetContent(
    isExpanded: Boolean = false,
    cornerSize: Dp = 12.dp,
    onDismiss: () -> Unit,
    content: @Composable () -> Unit,
) {
    val context = LocalContext.current
    val bottomSheetManager = remember { BottomSheetManager(context) }
    val lastIsExpanded = remember { mutableStateOf(isExpanded) }

    LaunchedEffect(isExpanded) {
        if (isExpanded != lastIsExpanded.value) {
            lastIsExpanded.value = isExpanded
            launch {
                delay(200) // Tempo de debounce (200ms)
                if (isExpanded == lastIsExpanded.value) {
                    if (isExpanded) {
                        bottomSheetManager.showBottomSheet(
                            cornerSize = cornerSize,
                            onDismiss = onDismiss,
                            content = content
                        )
                    } else {
                        bottomSheetManager.hideBottomSheet()
                    }
                }
            }
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            bottomSheetManager.hideBottomSheet()
        }
    }
}