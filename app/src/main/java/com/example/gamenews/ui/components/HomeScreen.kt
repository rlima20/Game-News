package com.example.gamenews.ui.components

import androidx.compose.foundation.background
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
import androidx.compose.ui.res.colorResource
import com.example.gamenews.R
import com.example.gamenews.model.GameNewsState
import com.example.gamenews.model.States
import com.example.gamenews.ui.RequestStatusProps
import com.example.gamenews.viewmodel.GameNewsViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@Composable
internal fun HomeScreen(gameNewsViewModel: GameNewsViewModel) {
    // ViewModel state variables
    val gameNewsUiState by gameNewsViewModel.uiState.collectAsState()
    val gameNewsUiStateFiltered by gameNewsViewModel.uiStateFiltered.collectAsState()
    val requestState by gameNewsViewModel.requestStatus.collectAsState()
    val shouldSearchFromAPI by gameNewsViewModel.shouldSearchFromAPI.collectAsState()
    val quantifier by gameNewsViewModel.quantifier.collectAsState()
    val advancedSearchBarText by gameNewsViewModel.advancedSearchBarText.collectAsState()
    val isScreenEnabled by gameNewsViewModel.isScreenEnabled.collectAsState()
    val shouldActivateAdvancedSearch by gameNewsViewModel.shouldActivateAdvancedSearch.collectAsState()

    // Local state variables
    var searchedText by remember { mutableStateOf("") }
    val localContext = LocalContext.current
    var advancedSearchIconClicked by remember { mutableStateOf(true) }

    val props = RequestStatusProps(
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
            gameNewsViewModel.setScreenEnabled(!advancedSearchIconClicked)
        },
        advancedSearchIconClickedValue = advancedSearchIconClicked,
        quantifierState = quantifier,
        advancedSearchBarText = advancedSearchBarText,
        onSaveAdvancedSearchStates = { quantifierState, advancedSearchBarText ->
            gameNewsViewModel.updateAdvancedSearchStates(
                quantifierState,
                advancedSearchBarText,
            )
        },
        shouldUseApi = shouldSearchFromAPI,
        isScreenEnabled = isScreenEnabled,
        shouldActivateAdvancedSearch = shouldActivateAdvancedSearch,
    )
    Row { SetHomeScreenColor(props = props) }
}

@Composable
fun SetHomeScreenColor(props: RequestStatusProps) {
    if (props.isScreenEnabled) {
        Column(
            Modifier.background(
                colorResource(id = R.color.disabled),
            ),
        ) {
            ValidateRequestStatus(props = props)
            props.gameNewsViewModel.trackAdvancedSearchViewed()
        }
    } else {
        ValidateRequestStatus(props = props)
    }
}

@Composable
private fun ValidateRequestStatus(props: RequestStatusProps) {
    when (props.requestStatus) {
        States.SUCCESS -> {
            if (props.listOfGameNewsUiState?.isNotEmpty() == true) {
                Column {
                    if (props.shouldActivateAdvancedSearch) {
                        AdvancedSearchComponent(
                            onSubmitButtonClicked = { itemsPerPage, query ->
                                props.gameNewsViewModel.fetchDataByQueryLocal(
                                    quantifier = itemsPerPage,
                                    query = query,
                                )
                            },
                            onAdvancedSearchIconClicked = {
                                props.onAdvancedSearchIconClicked()
                            },
                            onExitButtonCLicked = {
                                if (props.gameNewsViewModel.shouldSearchFromAPI.value) {
                                    props.gameNewsViewModel.fetchData()
                                } else {
                                    props.gameNewsViewModel.fetchLocalData()
                                }
                            },
                            advancedSearchIconClickedValue = !props.advancedSearchIconClickedValue,
                            onAdvancedSearchState = {
                                props.onSaveAdvancedSearchStates(
                                    it.first,
                                    it.second,
                                )
                            },
                            advancedSearchState = Pair(
                                props.quantifierState,
                                props.advancedSearchBarText,
                            ),
                        )
                    }
                    HomeScreenComponent(props)
                }
            } else {
                ErrorStateComponent(
                    onButtonClicked = {
                        CoroutineScope(Dispatchers.Main + Job()).launch {
                            props.gameNewsViewModel.fetchData()
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
                        props.gameNewsViewModel.fetchData()
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
