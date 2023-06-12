package com.example.gamenews.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.request.ImageRequest
import com.example.gamenews.extensions.getAsyncImagePainter
import com.example.gamenews.model.GameNewsState
import com.example.gamenews.model.States

@Composable
internal fun NewsItem(
    searchedText: String,
    gameNewsState: GameNewsState,
    imageRequest: ImageRequest,
    imageDialogFlag: Boolean,
    onClick: (value: Boolean) -> Boolean = { false }
) {
    var imageRequestState by remember { mutableStateOf(States.LOADING) }
    val painter = imageRequest.getAsyncImagePainter(
        onStateChanged = {
            imageRequestState = it
        }
    )

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 8.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = 4.dp,
        color = MaterialTheme.colors.background
    ) {
        Column {
            ImageSection(
                imageRequestState = imageRequestState,
                painter = painter,
                imageDialogFlag = imageDialogFlag
            ) { onClick(it) }
            TextSection(
                searchedWord = searchedText,
                gameNewsState = gameNewsState
            )
        }
    }
}
