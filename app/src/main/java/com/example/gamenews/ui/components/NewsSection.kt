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
    onImageRequested: (imageUrl: String) -> ImageRequest,
    searchedText: String,
    onClick: (bool: Boolean) -> Boolean = { false },
    imageDialog: Boolean
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        items(listOfGameNewsState) { gameNewsState ->
            NewsItem(
                searchedText = searchedText,
                gameNewsState = gameNewsState,
                imageRequest = onImageRequested(gameNewsState.image),
                onClick = { onClick(it) },
                imageDialog = imageDialog
            )
        }
    }
}
