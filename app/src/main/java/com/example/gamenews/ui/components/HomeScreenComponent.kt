package com.example.gamenews.ui.components

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import com.example.gamenews.model.GameNewsState
import com.example.gamenews.viewmodel.GameNewsViewModel

@Composable
fun HomeScreenComponent(
    searchedText: String,
    onSearchTextChanged: (searchText: String) -> Unit,
    listOfGameNewsState: List<GameNewsState>,
    gameNewsViewModel: GameNewsViewModel,
    localContext: Context,
) {
    SearchBarComponent(
        text = searchedText,
        onValueChange = { typedText ->
            onSearchTextChanged(typedText)
        },
        onCloseIconClicked = { gameNewsViewModel.clearFilteredListOfGameNews() },
        onSearched = {
            gameNewsViewModel.filterListOfGameNews(searchedText)

            if (gameNewsViewModel.uiStateFiltered.value?.isEmpty() == true) {
                Toast.makeText(
                    localContext, "No result for the search",
                    Toast
                        .LENGTH_SHORT
                ).show()
            }
        }
    )

    NewsSection(
        listOfGameNewsState = listOfGameNewsState,
        onImageRequested = { imageUrl ->
            gameNewsViewModel.getAsyncImage(
                imageUrl = imageUrl,
                context = localContext
            )
        },
        searchedText = searchedText
    )
}
