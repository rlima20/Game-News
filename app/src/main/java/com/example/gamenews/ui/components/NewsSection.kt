package com.example.gamenews.ui.components

import android.graphics.Typeface
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
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
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.request.ImageRequest
import com.example.gamenews.R
import com.example.gamenews.extensions.getAsyncImagePainter
import com.example.gamenews.model.GameNewsState
import com.example.gamenews.model.States

@Composable
internal fun NewsSection(
    listOfNews: List<GameNewsState>,
    onImageRequested: (imageUrl: String) -> ImageRequest
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        items(listOfNews) { news ->
            NewsItem(
                news = news,
                imageRequest = onImageRequested(news.image)
            )
        }
    }
}

@Composable
fun NewsItem(
    news: GameNewsState,
    imageRequest: ImageRequest
) {
    var state by remember { mutableStateOf(States.LOADING) }
    val painter = imageRequest.getAsyncImagePainter(
        onStateChanged = {
            state = it
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
            ImageSection(state, painter)
            TextSection(news)
        }
    }
}

@Composable
private fun ImageSection(
    state: States,
    painter: Painter
) {
    when (state) {
        States.SUCCESS -> {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                painter = painter,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }
        States.LOADING -> {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
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

@Composable
private fun TextSection(news: GameNewsState) {
    Column(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Text(
            text = news.title,
            color = colorResource(id = R.color.title_color),
            fontSize = 22.sp,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Bold,
        )

        Text(
            text = news.date,
            color = colorResource(id = R.color.title_color),
            fontSize = 16.sp,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Bold,
            maxLines = 1
        )
    }

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = news.description,
            fontSize = 18.sp,
            fontFamily = FontFamily(Typeface.SANS_SERIF)
        )
        Text(
            modifier = Modifier.clickable(
                enabled = true,
                onClickLabel = news.link,
                role = Role.Button,
                onClick = {},
            ),
            text = news.link,
            textDecoration = TextDecoration.Underline,
            color = colorResource(id = R.color.purple_700)
        )
    }
}
