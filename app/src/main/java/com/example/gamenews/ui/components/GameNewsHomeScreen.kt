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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.gamenews.R
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
                        if (gameNewsUiState?.isNotEmpty() == true) {
                            SearchBarComponent(searchBarText) { searchBarText = it }
                            gameNewsUiState?.let {
                                NewsSection(
                                    listOfNews = it,
                                    onImageRequested = { imageUrl ->
                                        gameNewsViewModel.getAsyncImage(
                                            imageUrl = imageUrl,
                                            context = localContext
                                        )
                                    }
                                )
                            }
                            // todo - passar por aqui somente se não tiver status de erro
                        } else {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    color = colorResource(id = R.color.black),
                                    fontSize = 32.sp,
                                    text = "error"
                                )
                            }
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
                    // todo - Não está passando por aqui. Colocar uma validação de null lá no
                    //  viewModel.
                    States.ERROR -> {
                        if (gameNewsUiState?.isEmpty() == true) {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    color = colorResource(id = R.color.black),
                                    fontSize = 32.sp,
                                    fontStyle = FontStyle.Normal,
                                    fontWeight = FontWeight.Bold,
                                    text = "Error"
                                )
                                Text(
                                    color = colorResource(id = R.color.black),
                                    fontSize = 18.sp,
                                    text = "Oops, something went wrong. Please try again later"
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
