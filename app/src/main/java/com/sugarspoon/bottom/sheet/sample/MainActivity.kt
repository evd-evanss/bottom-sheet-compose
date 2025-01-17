package com.sugarspoon.bottom.sheet.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.sugarspoon.bottom.sheet.components.Button
import com.sugarspoon.bottom.sheet.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    private val activityViewModel by viewModels<ActivityViewModel>()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                Color.Transparent.hashCode(),
                Color.Transparent.hashCode()
            ),
        )
        setContent {
            val uiState by activityViewModel.uiState.collectAsState()
            AppTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.White)
                            .padding(innerPadding),
                    ) {
                        Text(
                            text = "Header",
                            modifier = Modifier.padding(16.dp),
                            style = MaterialTheme.typography.headlineMedium,
                            color = Color.Black
                        )

                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth()
                                .background(Color.Blue.copy(alpha = .10f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("Content")
                        }

                        Button(
                            text = "Open Bottom Sheet",
                            onClick = activityViewModel::expandBottomSheet,
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
                        )

                        SampleBottomSheet(
                            isExpanded = uiState.isExpanded,
                            onDismiss = activityViewModel::collapseBottomSheet,
                        )
                    }
                }
            }
        }
    }
}