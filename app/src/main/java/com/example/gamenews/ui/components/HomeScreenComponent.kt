package com.example.gamenews.ui.components

import android.content.Context
import androidx.compose.runtime.Composable
import com.example.gamenews.model.GameNewsState
import com.example.gamenews.viewmodel.GameNewsViewModel

@Composable
fun HomeScreenComponent(
    searchBarText: String,
    onSearchTextChanged: (searchText: String) -> Unit,
    gameNewsUiState: List<GameNewsState>,
    gameNewsViewModel: GameNewsViewModel,
    localContext: Context
) {
    SearchBarComponent(
        text = searchBarText,
        onValueChange = {
            onSearchTextChanged(it)
        },
        onCloseIconClicked = { gameNewsViewModel.clearFilteredListOfGameNews() },
        onDoneKeyBoardClosed = { gameNewsViewModel.filterListOfGameNews(searchBarText) }
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
