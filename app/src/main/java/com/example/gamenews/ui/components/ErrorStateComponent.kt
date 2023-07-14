package com.example.gamenews.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gamenews.R

@Composable
fun ErrorStateComponent(
    onButtonClicked: () -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 32.dp, end = 32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            modifier = Modifier.size(180.dp, 180.dp),
            painter = painterResource(id = R.drawable.error_404),
            contentDescription = null,
        )

        Text(
            text = stringResource(id = R.string.game_news_error),
            color = colorResource(id = R.color.game_news_error_color),
            fontSize = 22.sp,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Bold,
        )

        Text(
            text = stringResource(id = R.string.game_news_something_went_wrong),
            color = colorResource(id = R.color.game_news_title_color),
            fontSize = 18.sp,
            modifier = Modifier.padding(bottom = 8.dp),
            textAlign = TextAlign.Center,
        )

        Button(
            enabled = true,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = colorResource(id = R.color.game_news_try_again_button_color),
            ),
            onClick = { onButtonClicked() },
        ) {
            Text(
                text = stringResource(id = R.string.game_news_try_again),
            )
        }
    }
}

@Preview
@Composable
fun ErrorStateComponentPreview() {
    ErrorStateComponent()
}
