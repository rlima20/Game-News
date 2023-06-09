package com.example.gamenews.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import com.example.gamenews.R
import com.example.gamenews.model.States

@Composable
internal fun ImageSection(
    imageRequestState: States,
    painter: Painter
) {
    when (imageRequestState) {
        States.SUCCESS -> {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(id = R.dimen.game_news_image_height)),
                painter = painter,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }
        States.LOADING -> {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(id = R.dimen.game_news_image_height)),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    color = MaterialTheme.colors.onSurface,
                )
            }
        }
        else -> {}
    }
}
