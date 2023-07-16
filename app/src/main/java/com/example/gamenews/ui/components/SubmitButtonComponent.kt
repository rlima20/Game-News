package com.example.gamenews.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gamenews.R

@Composable
internal fun SubmitButtonComponent(
    width: Dp,
    onClick: () -> Unit,
) {
    Surface(
        modifier = Modifier
            .width(width)
            .height(48.dp)
            .padding(start = 16.dp, top = 8.dp, end = 16.dp)
            .padding(bottom = 8.dp),
        shape = RoundedCornerShape(
            topStart = 25.dp,
            topEnd = 25.dp,
            bottomStart = 25.dp,
            bottomEnd = 25.dp,
        ),
        elevation = 4.dp,
        color = colorResource(id = R.color.game_news_white_color),
    ) {
        TextButton(
            modifier = Modifier
                .height(38.dp)
                .width(400.dp)
                .background(colorResource(id = R.color.game_news_splash_activity_main_color)),
            onClick = { onClick() },
            shape = RoundedCornerShape(
                topStart = 5.dp,
                topEnd = 5.dp,
                bottomStart = 5.dp,
                bottomEnd = 5.dp,
            ),
        ) {
            Text(
                text = stringResource(R.string.game_news_advanced_search_submit_text),
                fontSize = 12.dp.value.sp,
                overflow = TextOverflow.Ellipsis,
                color = colorResource(id = R.color.game_news_white_color),
            )
        }
    }
}
