package com.example.gamenews.ui.components

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
import androidx.compose.ui.res.dimensionResource
import com.example.gamenews.R
import com.example.gamenews.model.States

@Composable
internal fun ImageSection(
    imageRequestState: States,
    painter: Painter,
    onClick: (value: Boolean) -> Boolean = { false },
    imageDialog: Boolean = false
) {
    var state by remember { mutableStateOf(imageDialog) }

    when (imageRequestState) {
        States.SUCCESS -> {
            TextButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(id = R.dimen.game_news_image_height)),
                onClick = {
                    onClick(true)
                    state = onClick(true)
                }
            ) {
                if (state) {
                    ImageDialog(
                        onClick = {
                            onClick(false)
                            state = onClick(false)
                        }
                    )
                }
            }
            /*Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(id = R.dimen.game_news_image_height))
                    .clickable { onClick() },
                painter = painter,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )*/
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
