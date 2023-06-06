package com.example.gamenews.ui.components

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
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
internal fun HomeScreen(gameNewsViewModel: GameNewsViewModel) {

    val gameNewsUiState by gameNewsViewModel.uiState.collectAsState()
    val filteredGameNewsUiState by gameNewsViewModel.filterUiState.collectAsState()
    val requestState by gameNewsViewModel.requestState.collectAsState()
    var searchBarText by remember { mutableStateOf("") }
    val localContext = LocalContext.current
    val searchFromAPI by gameNewsViewModel.searchFromAPI.collectAsState()

    Row {
        Column {
            if (searchFromAPI) {
                ValidateRequestState(
                    requestState,
                    setUiState(filteredGameNewsUiState, gameNewsUiState),
                    searchBarText,
                    gameNewsViewModel,
                    localContext,
                    onSearchTextChanged = { searchBarText = it }
                )
            } else {
                HomeScreenComponent(
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

@Composable
private fun setUiState(
    filteredGameNewsUiState: List<GameNewsState>?,
    gameNewsUiState: List<GameNewsState>?
) = if (filteredGameNewsUiState?.isNotEmpty() == true) filteredGameNewsUiState else
    gameNewsUiState

@Composable
private fun ValidateRequestState(
    requestState: States,
    gameNewsUiState: List<GameNewsState>?,
    searchBarText: String,
    gameNewsViewModel: GameNewsViewModel,
    localContext: Context,
    onSearchTextChanged: (searchText: String) -> Unit,
) {
    when (requestState) {
        States.SUCCESS -> {
            if (gameNewsUiState?.isNotEmpty() == true) {
                HomeScreenComponent(
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
