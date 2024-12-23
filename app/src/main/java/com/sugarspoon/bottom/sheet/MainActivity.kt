package com.sugarspoon.bottom.sheet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sugarspoon.bottom.sheet.ui.theme.BottomSheetTheme
import com.sugarspoon.bottom.sheets.BottomSheet

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BottomSheetTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.White)
                            .padding(innerPadding),
                    ) {
                        val isExpanded = remember { mutableStateOf(false) }
                        Text(
                            text = "Bottom Sheet",
                            modifier = Modifier.padding(16.dp),
                            style = MaterialTheme.typography.headlineMedium,
                            color = Color.Black
                        )
                        Spacer(Modifier.weight(1f))
                        Button(
                            onClick = { isExpanded.value = !isExpanded.value },
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
                        ) {
                            Text("Toggle Bottom Sheet")
                        }

                        BottomSheet(
                            modifier = Modifier,
                            isExpanded = isExpanded.value,
                            onDismiss = {
                                isExpanded.value = false
                            },
//                            topSession = {
//                                Box(
//                                    Modifier
//                                        .height(100.dp)
//                                        .fillMaxWidth()
//                                        .background(Color.Red.copy(alpha = .30f)),
//                                    contentAlignment = Alignment.Center
//                                ) {
//                                    Text("Top Session")
//                                }
//                            },
                            content = {
                                Box(
                                    Modifier
                                        .height(100.dp)
                                        .fillMaxWidth()
                                        .background(Color.Green.copy(alpha = .30f)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text("Middle Session")
                                }
                            },
//                            bottomSession = {
//                                Box(
//                                    Modifier
//                                        .height(100.dp)
//                                        .fillMaxWidth()
//                                        .background(Color.Blue.copy(alpha = .30f)),
//                                    contentAlignment = Alignment.Center
//                                ) {
//                                    Text("Bottom Session")
//                                }
//                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BottomSheetTheme {
        Greeting("Android")
    }
}