package com.sugarspoon.bottom.sheets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
internal fun BottomSheetContent(
    isExpanded: Boolean = false,
    cornerSize: Dp = 12.dp,
    onDismiss: () -> Unit,
    content: @Composable () -> Unit,
) {
    val context = LocalContext.current
    val bottomSheetManager = remember { BottomSheetManager(context) }

    LaunchedEffect(isExpanded) {
        if (isExpanded) {
            bottomSheetManager.showBottomSheet(
                cornerSize = cornerSize,
                onDismiss = onDismiss,
                content = content
            )
        }
    }

    DisposableEffect(isExpanded) {
        if (!isExpanded) {
            bottomSheetManager.hideBottomSheet()
        }
        onDispose {
            bottomSheetManager.hideBottomSheet()
        }
    }
}