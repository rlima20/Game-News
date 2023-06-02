package com.example.gamenews.ui.components

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.gamenews.model.GameNewsState
import com.example.gamenews.model.States
import com.example.gamenews.viewmodel.GameNewsViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@Composable
internal fun GameNewsHomeScreen(gameNewsViewModel: GameNewsViewModel) {

    val gameNewsUiState by gameNewsViewModel.uiState.collectAsState()
    val requestState by gameNewsViewModel.requestState.collectAsState()
    val searchBarText by remember { mutableStateOf("") }
    val localContext = LocalContext.current

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Row {
            Column {
                ValidateRequestState(
                    requestState,
                    gameNewsUiState,
                    searchBarText,
                    gameNewsViewModel,
                    localContext
                )
            }
        }
    }
}

@Composable
private fun ValidateRequestState(
    requestState: States,
    gameNewsUiState: List<GameNewsState>?,
    searchBarText: String,
    gameNewsViewModel: GameNewsViewModel,
    localContext: Context
) {
    var searchText = searchBarText
    when (requestState) {
        States.SUCCESS -> {
            if (gameNewsUiState?.isNotEmpty() == true) {
                SearchBarComponent(searchText) { searchText = it }
                NewsSection(
                    listOfNews = gameNewsUiState,
                    onImageRequested = { imageUrl ->
                        gameNewsViewModel.getAsyncImage(
                            imageUrl = imageUrl,
                            context = localContext
                        )
                    }
                )
            } else {
                ErrorStateComponent(
                    onButtonCLicked = {
                        CoroutineScope(Dispatchers.Main + Job()).launch {
                            gameNewsViewModel.fetchData()
                        }
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
        States.ERROR -> {
            ErrorStateComponent(
                onButtonCLicked = {
                    CoroutineScope(Dispatchers.Main + Job()).launch {
                        gameNewsViewModel.fetchData()
                    }
                }
            )
        }
    }
}
