package com.example.gamenews.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.gamenews.SearchBarComponent
import com.example.gamenews.model.States
import com.example.gamenews.viewmodel.GameNewsViewModel

@Composable
internal fun GameNewsHomeScreen(gameNewsViewModel: GameNewsViewModel) {

    val gameNewsUiState by gameNewsViewModel.uiState.collectAsState()
    val requestState by gameNewsViewModel.requestState.collectAsState()
    var searchBarText by remember { mutableStateOf("") }
    val localContext = LocalContext.current

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Row {
            Column {
                when (requestState) {
                    States.SUCCESS -> {
                        if (gameNewsUiState.isNotEmpty()) {
                            SearchBarComponent(searchBarText) { searchBarText = it }
                            NewsSection(
                                listOfNews = gameNewsUiState,
                                onImageRequested = { imageUrl ->
                                    gameNewsViewModel.getAsyncImage(
                                        imageUrl = imageUrl,
                                        context = localContext
                                    )
                                }
                            )
                        }
                    }
                    States.LOADING -> {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            CircularProgressIndicator(
                                color = MaterialTheme.colors.onSurface,
                            )
                        }
                    }
                    else -> {
                        Text(text = "error")
                    }
                }
            }
        }
    }
}
