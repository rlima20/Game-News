package com.example.gamenews.ui.components

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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.gamenews.R
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
        modifier = Modifier.fillMaxSize(),
        shape = RoundedCornerShape(15.dp),
        elevation = 4.dp,
        color = MaterialTheme.colors.background
    ) {
        Column {
            AsyncImage(
                model = news.image,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.logo),
            )
            Column(
                modifier = Modifier.padding(16.dp)
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
                    text = news.date,
                    color = colorResource(id = R.color.title_color),
                    fontSize = 12.sp,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1
                )

                Text(
                    text = news.description
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

@Preview
@Composable
private fun Preview() {
    NewsSection()
}
