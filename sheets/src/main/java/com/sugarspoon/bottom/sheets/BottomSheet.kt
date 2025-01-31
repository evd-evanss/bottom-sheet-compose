package com.sugarspoon.bottom.sheets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * ### BottomSheet
 *
 * [isExpanded] This parameter controls the expanded state of the component. It defaults to false, indicating the component is initially collapsed.
 *
 * [cornerSize] This parameter likely defines the corner radius for rounded corners in the component's UI. It defaults to 12 density-independent pixels (dp).
 *
 * [paddingValues] This parameter sets the padding around the content of the component. It defaults to 16 dp of horizontal padding.
 *
 * [content] This parameter accepts a composable function that defines the content of the top section of the component. It defaults to an empty lambda, meaning no content is displayed in the top section by default.
 *
 * [onDismiss] This parameter accepts a function that is called when the component should be dismissed or hidden. It defaults to an empty lambda, meaning no action is taken by default when dismissal is triggered.
 *
 * ``` kotlin
 * @Preview
 * @Composable
 * private fun BottomSheetPreview() = MaterialTheme {
 *     Column(
 *         modifier = Modifier
 *             .fillMaxSize()
 *             .background(Color.White),
 *     ) {
 *         val isExpanded = remember { mutableStateOf(false) }
 *         Spacer(Modifier.weight(1f))
 *         Button(
 *             onClick = { isExpanded.value = !isExpanded.value },
 *             modifier = Modifier.fillMaxWidth()
 *         ) {
 *             Text("Toggle Bottom Sheet")
 *         }
 *
 *         BottomSheet(
 *             isExpanded = isExpanded.value,
 *             onDismiss = {
 *                 isExpanded.value = false
 *             },
 *             content = {
 *                 Box(
 *                     Modifier
 *                         .height(100.dp)
 *                         .fillMaxWidth()
 *                         .background(Color.Red.copy(alpha = .30f)),
 *                     contentAlignment = Alignment.Center
 *                 ) {
 *                     Text("Your content")
 *                 }
 *             },
 *         )
 *     }
 * }
 * ```
 */
@Composable
fun BottomSheet(
    modifier: Modifier = Modifier,
    isExpanded: Boolean = false,
    cornerSize: Dp = 12.dp,
    paddingValues: PaddingValues = PaddingValues(horizontal = 16.dp),
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    onDismiss: () -> Unit = {},
    content: @Composable ColumnScope.() -> Unit = {},
) {
    BottomSheetContent(
        cornerSize = cornerSize,
        isExpanded = isExpanded,
        onDismiss = onDismiss
    ) {
        Column(
            modifier = modifier.padding(paddingValues),
            verticalArrangement = verticalArrangement,
            horizontalAlignment = horizontalAlignment,
        ) {
            content()
        }
    }
}

@Preview
@Composable
private fun BottomSheetPreview() = MaterialTheme {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
    ) {
        val isExpanded = remember { mutableStateOf(false) }
        Spacer(Modifier.weight(1f))
        Button(
            onClick = { isExpanded.value = !isExpanded.value },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Toggle Bottom Sheet")
        }

        BottomSheet(
            isExpanded = isExpanded.value,
            onDismiss = {
                isExpanded.value = false
            },
            content = {
                Box(
                    Modifier
                        .height(100.dp)
                        .fillMaxWidth()
                        .background(Color.Red.copy(alpha = .30f)),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Your content")
                }
            },
        )
    }
}