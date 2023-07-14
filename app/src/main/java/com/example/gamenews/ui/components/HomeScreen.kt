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
    val gameNewsUiStateFiltered by gameNewsViewModel.uiStateFiltered.collectAsState()
    val requestState by gameNewsViewModel.requestStatus.collectAsState()
    val shouldSearchFromAPI by gameNewsViewModel.shouldSearchFromAPI.collectAsState()
    var searchedText by remember { mutableStateOf("") }
    val localContext = LocalContext.current
    var advancedSearchIconClicked by remember { mutableStateOf(true) }

    Row {
        Column {
            if (shouldSearchFromAPI) {
                ValidateRequestStatus(
                    requestStatus = requestState,
                    listOfGameNewsUiState = getListOfGameNewsFilteredOrNot(
                        gameNewsUiStateFiltered,
                        gameNewsUiState,
                    ),
                    searchedText = searchedText,
                    gameNewsViewModel = gameNewsViewModel,
                    localContext = localContext,
                    onSearchTextChanged = { searchedText = it },
                    onAdvancedSearchIconClicked = {
                        advancedSearchIconClicked =
                            !advancedSearchIconClicked
                    },
                    advancedSearchIconClickedValue = advancedSearchIconClicked,
                )
            } else {
                HomeScreenComponent(
                    searchedText = searchedText,
                    listOfGameNewsState = listOfNews,
                    gameNewsViewModel = gameNewsViewModel,
                    localContext = localContext,
                    onSearchTextChanged = { searchedText = it },
                    onAdvancedSearchIconClicked = { advancedSearchIconClicked },
                    advancedSearchIconClickedValue = false,
                )
            }
        }
    }
}

@Composable
private fun ValidateRequestStatus(
    requestStatus: States,
    listOfGameNewsUiState: List<GameNewsState>?,
    searchedText: String,
    gameNewsViewModel: GameNewsViewModel,
    localContext: Context,
    onSearchTextChanged: (searchText: String) -> Unit,
    onAdvancedSearchIconClicked: () -> Unit,
    advancedSearchIconClickedValue: Boolean
) {
    when (requestStatus) {
        States.SUCCESS -> {
            if (listOfGameNewsUiState?.isNotEmpty() == true) {
                Column {
                    AdvancedSearchComponent(
                        onExitButtonClick = { !advancedSearchIconClickedValue },
                        onSubmitButtonClicked = { itemsPerPage, query ->
                            gameNewsViewModel.getListOfGameNewsByQueryAndItemsPerPage(
                                itemsPerPage = itemsPerPage,
                                query = query,
                            )
                        },
                    )
                    HomeScreenComponent(
                        searchedText = searchedText,
                        onSearchTextChanged = onSearchTextChanged,
                        listOfGameNewsState = listOfGameNewsUiState,
                        gameNewsViewModel = gameNewsViewModel,
                        localContext = localContext,
                        onAdvancedSearchIconClicked = { onAdvancedSearchIconClicked() },
                        advancedSearchIconClickedValue = advancedSearchIconClickedValue,
                    )
                }
            } else {
                ErrorStateComponent(
                    onButtonClicked = {
                        CoroutineScope(Dispatchers.Main + Job()).launch {
                            gameNewsViewModel.fetchData()
                        }
                    },
                )
            }
        }

        States.LOADING -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                CircularProgressIndicator(
                    color = MaterialTheme.colors.onSurface,
                )
            }
        }

        States.ERROR -> {
            ErrorStateComponent(
                onButtonClicked = {
                    CoroutineScope(Dispatchers.Main + Job()).launch {
                        gameNewsViewModel.fetchData()
                    }
                },
            )
        }
    }
}

@Composable
private fun getListOfGameNewsFilteredOrNot(
    filteredGameNewsUiState: List<GameNewsState>?,
    gameNewsUiState: List<GameNewsState>?,
) = if (filteredGameNewsUiState?.isNotEmpty() == true) {
    filteredGameNewsUiState
} else {
    gameNewsUiState
}
