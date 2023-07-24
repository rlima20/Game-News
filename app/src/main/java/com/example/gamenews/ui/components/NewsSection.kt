package com.example.gamenews.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.request.ImageRequest
import com.example.gamenews.model.GameNewsState

@Composable
internal fun NewsSection(
    listOfGameNewsState: List<GameNewsState>,
    searchedText: String,
    imageDialog: Boolean,
    isScreenEnabled: Boolean = false,
    onImageRequested: (imageUrl: String) -> ImageRequest,
    onClick: (bool: Boolean) -> Boolean = { false },
    onNewsSectionViewed: () -> Unit = {},
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        userScrollEnabled = !isScreenEnabled,
    ) {
        items(listOfGameNewsState) { gameNewsState ->
            NewsItem(
                searchedText = searchedText,
                gameNewsState = gameNewsState,
                imageDialogFlag = imageDialog,
                imageRequest = onImageRequested(gameNewsState.image),
                isScreenEnabled = isScreenEnabled,
            ) { onClick(it) }
        }
    }
    onNewsSectionViewed()
}
