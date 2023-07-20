package com.example.gamenews.ui.components

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.gamenews.R
import com.example.gamenews.provider.local.listOfNews
import com.example.gamenews.ui.RequestStatusProps

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun HomeScreenComponent(props: RequestStatusProps) {
    val noResult = stringResource(id = R.string.game_news_no_result_for_the_search)

    SearchBarComponent(
        text = props.searchedText,
        onValueChange = { typedText -> props.onSearchTextChanged(typedText) },
        onCloseIconClicked = { props.gameNewsViewModel.clearFilteredListOfGameNews() },
        onSearched = {
            props.gameNewsViewModel.filterListOfGameNews(props.searchedText)

            if (props.gameNewsViewModel.uiStateFiltered.value?.isEmpty() == true) {
                Toast.makeText(
                    props.localContext,
                    noResult,
                    Toast
                        .LENGTH_SHORT,
                ).show()
            }
        },
        onAdvancedSearchIconClicked = { props.onAdvancedSearchIconClicked() },
        advancedSearchIconClickedValue = props.advancedSearchIconClickedValue,
        shoudActivateAdvancedSearch = props.shouldActivateAdvancedSearch,
    )

    (
        // Isso é temporário, até que a API esteja funcionando
        if (props.shouldUseApi) {
            props.listOfGameNewsUiState
        } else {
            props.listOfGameNewsUiState
        }
        )?.let {
        NewsSection(
            searchedText = props.searchedText,
            listOfGameNewsState = it,
            imageDialog = props.gameNewsViewModel.imageDialog.value,
            onClick = { props.gameNewsViewModel.setImageDialog(it) },
            onImageRequested = { imageUrl ->
                props.gameNewsViewModel.getAsyncImage(
                    imageUrl = imageUrl,
                    context = props.localContext,
                )
            },
            isScreenEnabled = props.isScreenEnabled,
        )
        props.gameNewsViewModel.trackScreenViewed()
    }
}
