package com.example.gamenews.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.AlertDialog
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.example.gamenews.R

@Composable
fun ImageDialog(
    painter: Painter,
    onClick: () -> Unit = {},
) {
    AlertDialog(
        properties = DialogProperties(),
        modifier = Modifier
            .fillMaxWidth()
            .height(height = 220.dp),
        text = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    painter = painter,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillWidth,
                )
            }
        },
        shape = MaterialTheme.shapes.large,
        confirmButton = { },
        onDismissRequest = { },
        dismissButton = {
            IconButton(onClick = { onClick() }) { Text(text = "x") }
        },
    )
}

@Composable
@Preview
fun ImageDialogPreview() {
    ImageDialog(painter = painterResource(id = R.drawable.logo))
}
