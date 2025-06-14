package com.sugarspoon.bottom.sheet.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.sugarspoon.bottom.sheets.R

@Composable
fun Button(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
) {
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


@Composable
fun ButtonWithIcon(
    icon: Int,
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = MaterialTheme.shapes.small,
    ) {
        Row {
            Text(
                text = text,
                style = MaterialTheme.typography.bodyLarge,
            )
            Icon(
                painter = painterResource(icon),
                contentDescription = null
            )
        }
    }
}

@Preview
@Composable
private fun ButtonWithIconPreview() = MaterialTheme {
    ButtonWithIcon(
        icon = R.drawable.ic_android_black_24dp,
        text = "Continuar",
        onClick = {

        }
    )
}