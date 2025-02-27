@file:OptIn(ExperimentalMaterial3Api::class)

package dev.twango.fetch.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import dev.twango.fetch.ui.theme.FetchTakeHomeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FetchTakeHomeTheme {
                FetchTakeHomeApp()
            }
        }
    }
}
