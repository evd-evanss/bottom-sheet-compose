package com.sugarspoon.bottom.sheet.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import com.sugarspoon.bottom.sheet.components.Button
import com.sugarspoon.bottom.sheet.components.TopBar
import com.sugarspoon.bottom.sheet.ui.theme.AppTheme
import com.sugarspoon.bottom.sheets.BottomSheet

class MainActivity : ComponentActivity() {

    private val activityViewModel by viewModels<ActivityViewModel>()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
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
                            text = "App Sample",
                            modifier = Modifier.padding(16.dp),
                            style = MaterialTheme.typography.headlineMedium,
                            color = Color.Black
                        )

                        Spacer(Modifier.weight(1f))

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