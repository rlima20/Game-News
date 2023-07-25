package com.example.gamenews.extensions

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.gamenews.R
import com.example.gamenews.model.GameNewsDTO
import com.example.gamenews.model.States
import kotlinx.coroutines.flow.FlowCollector
import retrofit2.Response

/**
 * Get async image painter.
 * This extension returns a Painter with the image if the imaRequest has State.Success. If not,
 * it will return a generic painter.
 * @receiver [ImageRequest]
 * @return [Painter]
 */
@Composable
fun ImageRequest.getAsyncImagePainter(
    onStateChanged: (state: States) -> Unit = {},
): Painter {
    if (rememberAsyncImagePainter(this).state is AsyncImagePainter.State.Success) {
        onStateChanged(States.SUCCESS)
    } else if (rememberAsyncImagePainter(this).state is AsyncImagePainter.State.Loading) {
        onStateChanged(States.LOADING)
    } else {
        onStateChanged(States.ERROR)
    }

    return if (rememberAsyncImagePainter(this).state is AsyncImagePainter.State.Success) {
        rememberAsyncImagePainter(this)
    } else {
        painterResource(id = R.drawable.placeholder)
    }
}

fun String.removeSpaces(): String = this.replace(" ", "")

@Composable
fun getSpanStyles(
    mainText: String,
    searchedWord: String,
): List<AnnotatedString.Range<SpanStyle>> {
    val start = mainText.indexOf(searchedWord)
    return listOf(
        AnnotatedString.Range(
            SpanStyle(
                color = colorResource(id = R.color.game_news_white_color),
                background = colorResource(id = R.color.game_news_link_color),
            ),
            start = start,
            end = start + searchedWord.length,
        ),
    )
}

suspend fun FlowCollector<List<GameNewsDTO>?>.validateRequest(
    request:
    Response<List<GameNewsDTO>>?,
) {
    with(request) {
        Log.i("REQUEST", "Status code = ${this?.code()}")

        with(this) {
            val code = this?.code()
            if (code == 404 ||
                code == 403 ||
                code == 501 ||
                code == 500 ||
                code == 502
            ) {
                emit(null)
            } else if (this?.isSuccessful == true) {
                this.body()?.let { emit(it) }
            } else {
                emit(
                    emptyList(),
                )
            }
        }
    }
}
