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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.gamenews.model.GameNewsState
import com.example.gamenews.model.States
import com.example.gamenews.provider.local.listOfNews
import com.example.gamenews.viewmodel.GameNewsViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@Composable
internal fun GameNewsHomeScreen(gameNewsViewModel: GameNewsViewModel) {

    val gameNewsUiState by gameNewsViewModel.uiState.collectAsState()
    val requestState by gameNewsViewModel.requestState.collectAsState()
    var searchBarText by remember { mutableStateOf("") }
    val localContext = LocalContext.current
    val searchFromAPI = false

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Row {
            Column {
                if (searchFromAPI) {
                    ValidateRequestState(
                        requestState,
                        gameNewsUiState,
                        searchBarText,
                        gameNewsViewModel,
                        localContext,
                    ) { searchBarText = it }
                } else {
                    CreateHomeScreen(
                        searchBarText,
                        onSearchTextChanged = { searchBarText = it },
                        listOfNews,
                        gameNewsViewModel,
                        localContext
                    )
                }
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
    localContext: Context,
    onSearchTextChanged: (searchText: String) -> Unit
) {
    when (requestState) {
        States.SUCCESS -> {
            if (gameNewsUiState?.isNotEmpty() == true) {
                CreateHomeScreen(
                    searchBarText,
                    onSearchTextChanged,
                    gameNewsUiState,
                    gameNewsViewModel,
                    localContext
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

@Composable
private fun CreateHomeScreen(
    searchBarText: String,
    onSearchTextChanged: (searchText: String) -> Unit,
    gameNewsUiState: List<GameNewsState>,
    gameNewsViewModel: GameNewsViewModel,
    localContext: Context
) {
    SearchBarComponent(
        text = searchBarText,
        onValueChange = { onSearchTextChanged(it) }
    )
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
