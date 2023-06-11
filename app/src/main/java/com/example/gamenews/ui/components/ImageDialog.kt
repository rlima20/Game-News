package com.example.gamenews.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.AlertDialog
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gamenews.R

@Composable
fun ImageDialog(
    onClick: () -> Unit = {}
) {
    AlertDialog(
        onDismissRequest = { /* Ação quando o dialog é fechado */ },
        text = {
            Column(
                modifier = Modifier.padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(R.drawable.no_conn),
                    contentDescription = null, // Descrição opcional da imagem
                    modifier = Modifier.size(120.dp)
                )
            }
        },
        confirmButton = {
            // Ação para o botão de confirmação
        },
        dismissButton = {
            IconButton(onClick = {
                onClick()
            }) {
                Text(text = "x")
            }
        }
    )
}

@Composable
@Preview
fun ImageDialogPreview() {
    ImageDialog()
}
