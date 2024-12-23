package com.sugarspoon.bottom.sheets

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

internal class BottomSheetManager(private val context: Context) {

    private var bottomSheetView: ComposeView? = null
    private var isVisible : MutableState<Boolean> = mutableStateOf(false)
    private val dimView = View(context).apply {
        setBackgroundColor(Color.Black.copy(alpha = 0.6f).toArgb())
    }

    fun showBottomSheet(
        cornerSize: Dp,
        onDismiss: () -> Unit,
        content: @Composable () -> Unit,
    ) {
        if (bottomSheetView == null) {
            bottomSheetView = ComposeView(context).apply {
                setContent {
                    LaunchedEffect(true) {
                        launch {
                            delay(100)
                            isVisible.value = true
                        }
                    }

                    AnimatedVisibility(
                        visible = isVisible.value,
                        enter = slideInVertically(
                            animationSpec = tween(
                                durationMillis = 400,
                                easing = EaseIn
                            )
                        ) {
                            it
                        },
                        exit = slideOutVertically(
                            animationSpec = tween(
                                durationMillis = 400,
                                easing = EaseOut
                            )
                        ) {
                            it
                        }
                    ) {
                        Box(
                            modifier = Modifier
                                .draggableBottomSheetContent {
                                    onDismiss()
                                }
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(topStart = cornerSize, topEnd = cornerSize))
                                .background(Color.White)
                        ) {
                            content()
                        }
                    }
                }
            }
        }

        val parentView = (context as Activity).findViewById<ViewGroup>(
            android.R.id.content
        )

        val layoutParams = FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
        ).apply {
            gravity = android.view.Gravity.BOTTOM
        }

        // Verifica se o ComposeView já tem um pai
        (bottomSheetView?.parent as? ViewGroup)?.removeView(bottomSheetView)

        parentView.addView(dimView, layoutParams)
        parentView.addView(bottomSheetView, layoutParams)
        dimView.setOnClickListener {
            onDismiss()
        }
    }

    fun hideBottomSheet() {
        CoroutineScope(Dispatchers.Main).launch {
            bottomSheetView?.let {
                isVisible.value = false
                val parentView = it.parent as? ViewGroup
                parentView?.removeView(dimView)
                delay(200)
                parentView?.removeView(it)
                bottomSheetView = null
            }
        }
    }

    @SuppressLint("ModifierFactoryUnreferencedReceiver")
    private fun Modifier.draggableBottomSheetContent(
        onDismiss: () -> Unit
    ): Modifier = composed {
        var dragOffset by remember { mutableStateOf(0f) }
        var initialOffset by remember { mutableStateOf(0f) }

        pointerInput(Unit) {
            detectVerticalDragGestures(
                onDragStart = { offset ->
                    initialOffset = dragOffset
                },
                onDragEnd = {
                    if (dragOffset <= size.height * 0.8f) {
                        dragOffset = initialOffset
                    }
                },
                onVerticalDrag = { _, dragAmount ->
                    val newOffset = dragOffset + dragAmount
                    dragOffset = newOffset.coerceAtLeast(initialOffset)
                    if (newOffset > size.height * 0.8f) {
                        onDismiss()
                    }
                }
            )
        }
            .offset { IntOffset(x = 0, y = dragOffset.toInt()) }
    }
}