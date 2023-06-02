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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gamenews.R

@Composable
fun ErrorStateComponent(
    onButtonCLicked: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 32.dp, end = 32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.size(180.dp, 180.dp),
            painter = painterResource(id = R.drawable.oops),
            contentDescription = null
        )

        Text(
            color = colorResource(id = R.color.error_color),
            fontSize = 22.sp,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Bold,
            text = "Error!"
        )
        Text(
            modifier = Modifier.padding(bottom = 8.dp),
            textAlign = TextAlign.Center,
            color = colorResource(id = R.color.title_color),
            fontSize = 18.sp,
            text = "Something went wrong. Please try again later"
        )
        Button(
            enabled = true,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = colorResource(id = R.color.try_again_button_color),
            ),
            onClick = { onButtonCLicked() }
        ) {
            Text(
                text = "Try again",
            )
        }
    }
}
