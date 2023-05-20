package com.example.gamenews.ui.components

import android.content.Context
import android.graphics.Typeface
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.gamenews.R
import com.example.gamenews.extensions.formatDateToDateNews
import com.example.gamenews.listOfNews
import com.example.gamenews.model.News

@Composable
internal fun NewsSection() {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        items(listOfNews) { news ->
            NewsItem(news = news)
        }
    }
}

@Composable
fun NewsItem(news: News) {
    Surface(
        modifier = Modifier.fillMaxSize().padding(bottom = 8.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = 4.dp,
        color = MaterialTheme.colors.background
    ) {
        Column {
            Image(
                painter = getAsyncImagePainter(news, LocalContext.current),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            )

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
                    maxLines = 1
                )

                Text(
                    text = news.date.formatDateToDateNews().toString(),
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
                    text = news.link,
                    textDecoration = TextDecoration.Underline,
                    color = colorResource(id = R.color.purple_700)
                )
            }
        }
    }
}

// todo - Essa função vai para o viewmodel.
@Composable
private fun getAsyncImagePainter(news: News, context: Context): Painter {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(context)
            .data(news.image)
            .size(Size.ORIGINAL)
            .crossfade(true)
            .build()
    )

    return if (painter.state is AsyncImagePainter.State.Success) {
        painter
    } else {
        painterResource(id = R.drawable.placeholder)
    }
}

@Preview
@Composable
private fun Preview() {
    NewsSection()
}
