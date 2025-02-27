package dev.twango.fetch.main

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun FetchTakeHomeApp() {

    Scaffold(
        topBar = {
            FetchTakeHomeTopBar()
        },
        modifier = Modifier.fillMaxSize(),
    ) { innerPadding ->
        FetchTakeHomeContent(innerPadding)
    }

}

@Composable
fun FetchTakeHomeTopBar() {

}

@Composable
fun FetchTakeHomeContent(innerPadding: PaddingValues) {

}



