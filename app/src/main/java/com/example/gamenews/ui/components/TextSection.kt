package com.example.gamenews.ui.components

import android.graphics.Typeface
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gamenews.R
import com.example.gamenews.model.GameNewsState

@Composable
internal fun TextSection(news: GameNewsState) {
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
