package com.example.gamenews.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import com.example.gamenews.R
import com.example.gamenews.model.States

@Composable
internal fun ImageSection(
    imageRequestState: States,
    painter: Painter,
    imageDialogFlag: Boolean = false,
    onClick: (value: Boolean) -> Boolean = { false },
) {
    var showImageDialogState by remember { mutableStateOf(imageDialogFlag) }

    when (imageRequestState) {
        States.SUCCESS -> {
            Box {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(dimensionResource(id = R.dimen.game_news_image_height)),
                    painter = painter,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                )
                TextButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(colorResource(id = R.color.game_news_transparent_color))
                        .height(dimensionResource(id = R.dimen.game_news_image_height)),
                    onClick = {
                        onClick(true)
                        showImageDialogState = onClick(true)
                    },
                ) {
                    if (showImageDialogState) {
                        ImageDialog(
                            painter = painter,
                            onClick = {
                                onClick(false)
                                showImageDialogState = onClick(false)
                            },
                        )
                    }
                }
            }
        }

        States.LOADING -> {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(id = R.dimen.game_news_image_height)),
                contentAlignment = Alignment.Center,
            ) {
                CircularProgressIndicator(
                    color = MaterialTheme.colors.onSurface,
                )
            }
        }

        else -> {}
    }
}
