package com.sugarspoon.bottom.sheets

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.view.Gravity
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.PopupWindow
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.union
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

internal class BottomSheetManager(private val context: Context) : PopupWindow(context){

    private var bottomSheetView: ComposeView? = null
    private var isVisible: MutableState<Boolean> = mutableStateOf(false)

    fun showBottomSheet(
        cornerSize: Dp,
        onDismiss: () -> Unit,
        content: @Composable () -> Unit,
    ) {
        val parentView = (context as Activity).findViewById<ViewGroup>(
            android.R.id.content
        )
        if (bottomSheetView == null) {
            bottomSheetView = ComposeView(context).apply {
                setContent {

                    LaunchedEffect(true) {
                        launch {
                            delay(50)
                            isVisible.value = true
                        }
                    }
                    val window = (context as Activity).window
                    window.setBackgroundDrawableResource(android.R.color.transparent)

                    Box(
                        contentAlignment = Alignment.BottomCenter
                    ) {
                        DimView(onDismiss =  onDismiss)
                        AnimatedVisibility(
                            modifier = Modifier,
                            visible = isVisible.value,
                            enter = slideInVertically(
                                animationSpec = tween(
                                    durationMillis = 350,
                                    easing = EaseIn
                                )
                            ) {
                                it
                            },
                            exit = slideOutVertically(
                                animationSpec = tween(
                                    durationMillis = 350,
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
                                    .clip(
                                        RoundedCornerShape(
                                            topStart = cornerSize,
                                            topEnd = cornerSize
                                        )
                                    )
                                    .background(Color.White),
                                contentAlignment = Alignment.BottomCenter
                            ) {
                                content()
                            }
                        }
                    }
                }
            }
        }

        val layoutParams = FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
        ).apply {
            gravity = Gravity.BOTTOM
        }

        (bottomSheetView?.parent as? ViewGroup)?.removeView(bottomSheetView)

        parentView.addView(bottomSheetView, layoutParams)
    }

    fun hideBottomSheet() {
        CoroutineScope(Dispatchers.Main).launch {
            bottomSheetView?.let {
                isVisible.value = false
                val parentView = it.parent as? ViewGroup
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

@Composable
fun DimView(
    dimAmount: Float = 0.6f,
    isVisible: Boolean = true,
    onDismiss: () -> Unit = {}
) {
    if (isVisible) {
        Box(
            modifier = Modifier
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) {
                    onDismiss()
                }
                .fillMaxSize()
                .windowInsetsPadding(
                    WindowInsets.statusBars.union(WindowInsets.navigationBars)
                        .exclude(WindowInsets.statusBars.union(WindowInsets.navigationBars))
                )
                .background(Color.Black.copy(alpha = dimAmount))
        )
    }
}

