package com.example.gamenews.extensions

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.gamenews.R

/**
 * Format date to date news.
 * This extension returns a string from the first letter until to letter on index 16
 * @receiver
 */
fun String.formatDateToDateNews() {
    this.substring(startIndex = 0, endIndex = 16)
}

/**
 * Get async image painter.
 * This extension returns a Painter with the image if the imaRequest has State.Success. If not,
 * it will return a generic painter.
 * @receiver [ImageRequest]
 * @return [Painter]
 */
@Composable
fun ImageRequest.getAsyncImagePainter(): Painter =
    if (rememberAsyncImagePainter(this).state is AsyncImagePainter.State.Success) {
        rememberAsyncImagePainter(this)
    } else {
        painterResource(id = R.drawable.placeholder)
    }
