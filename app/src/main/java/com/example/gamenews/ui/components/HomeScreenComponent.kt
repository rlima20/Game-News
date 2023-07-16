package com.example.gamenews.ui.components

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.gamenews.R
import com.example.gamenews.model.GameNewsState
import com.example.gamenews.viewmodel.GameNewsViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun HomeScreenComponent(
    searchedText: String,
    onSearchTextChanged: (searchText: String) -> Unit,
    listOfGameNewsState: List<GameNewsState>,
    gameNewsViewModel: GameNewsViewModel,
    localContext: Context,
    onAdvancedSearchIconClicked: () -> Unit,
    advancedSearchIconClickedValue: Boolean,
) {
    val noResult = stringResource(id = R.string.game_news_no_result_for_the_search)

    SearchBarComponent(
        text = searchedText,
        onValueChange = { typedText -> onSearchTextChanged(typedText) },
        onCloseIconClicked = { gameNewsViewModel.clearFilteredListOfGameNews() },
        onSearched = {
            gameNewsViewModel.filterListOfGameNews(searchedText)

            if (gameNewsViewModel.uiStateFiltered.value?.isEmpty() == true) {
                Toast.makeText(
                    localContext,
                    noResult,
                    Toast
                        .LENGTH_SHORT,
                ).show()
            }
        },
        onAdvancedSearchIconClicked = { onAdvancedSearchIconClicked() },
        advancedSearchIconClickedValue = advancedSearchIconClickedValue,
    )

    NewsSection(
        searchedText = searchedText,
        listOfGameNewsState = listOfGameNewsState,
        imageDialog = gameNewsViewModel.imageDialog.value,
        onClick = { gameNewsViewModel.setImageDialog(it) },
        onImageRequested = { imageUrl ->
            gameNewsViewModel.getAsyncImage(
                imageUrl = imageUrl,
                context = localContext,
            )
        },
        enabled = advancedSearchIconClickedValue
    )
}
