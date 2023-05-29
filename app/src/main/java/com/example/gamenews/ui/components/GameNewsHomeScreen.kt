package com.example.gamenews.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.example.gamenews.R
import com.example.gamenews.SearchBarComponent
import com.example.gamenews.viewmodel.GameNewsViewModel

@Composable
internal fun GameNewsHomeScreen(
    gameNewsViewModel: GameNewsViewModel,
    hasInternet: Boolean
) {

    val gameNewsUiState by gameNewsViewModel.uiState.collectAsState()
    var text by remember { mutableStateOf("") }
    val context = LocalContext.current

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Row {
            Column {
                if (hasInternet) {

                    gameNewsViewModel.hasInternetState = true
                    LaunchedEffect(key1 = "") {
                        gameNewsViewModel.getListOfNews()
                    }

                    SearchBarComponent(text) { text = it }
                    if (gameNewsUiState.isNotEmpty()) {
                        NewsSection(
                            listOfNews = gameNewsUiState,
                            onImageRequested = { imageUrl ->
                                gameNewsViewModel.getAsyncImage(
                                    imageUrl = imageUrl,
                                    context = context
                                )
                            }
                        )
                    } else {
                        WarningComponent(
                            message = stringResource(id = R.string.empty_list_string),
                            buttonText = stringResource(id = R.string.try_again_text_string),
                            enabledTryAgainButton = true,
                            onTryAgain = {}
                        )
                    }
                } else {
                    gameNewsViewModel.hasInternetState = false
                    WarningComponent(
                        message = stringResource(id = R.string.no_internet_connection_string),
                        buttonText = stringResource(id = R.string.try_again_text_string),
                        enabledTryAgainButton = true,
                        onTryAgain = {}
                    )
                }
            }
        }
    }
}
