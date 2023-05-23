package com.example.gamenews.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.gamenews.SearchBarComponent
import com.example.gamenews.viewmodel.GameNewsViewModel

@Composable
internal fun GameNewsHomeScreen(gameNewsViewModel: GameNewsViewModel) {

    val gameNewsUiState by gameNewsViewModel.uiState.collectAsState()

    var text by remember { mutableStateOf("") }
    val context = LocalContext.current

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Row {
            Column {
                SearchBarComponent(text) { text = it }
                NewsSection(
                    listOfNews = gameNewsUiState,
                    onImageRequested = {
                        gameNewsViewModel.getAsyncImage(
                            imageUrl = it,
                            context = context
                        )
                    }
                )
            }
        }
    }
}
