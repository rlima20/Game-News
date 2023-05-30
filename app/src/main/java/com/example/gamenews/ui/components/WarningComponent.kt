package com.example.gamenews.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gamenews.R

@Composable
fun WarningComponent(
    message: String,
    buttonText: String,
    enabledTryAgainButton: Boolean,
    onTryAgain: () -> Unit = {}
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(start = 24.dp, end = 24.dp),
            text = message,
            color = colorResource(id = R.color.title_color),
            fontSize = 16.sp,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Center
        )
        Button(
            modifier = Modifier
                .padding(top = 16.dp),
            enabled = enabledTryAgainButton,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = colorResource(id = R.color.no_connection_background)
            ),
            onClick = { onTryAgain() }
        ) {
            Text(text = buttonText)
        }
    }
}

@Preview
@Composable
fun Preview() {
    WarningComponent(
        message = stringResource(id = R.string.no_internet_connection_string),
        buttonText = stringResource(id = R.string.try_again_text_string),
        enabledTryAgainButton = true,
        onTryAgain = {}
    )
}
