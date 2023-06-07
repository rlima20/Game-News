package com.example.gamenews.ui.components

import android.graphics.Typeface
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gamenews.R
import com.example.gamenews.extensions.formatWordSearchedToBold
import com.example.gamenews.model.GameNewsState

@Composable
fun formatarPalavraEmNegrito(texto: String, palavra: String): AnnotatedString {
    val resultado = buildAnnotatedString {
        val startIndex = texto.indexOf(palavra, ignoreCase = true)
        val endIndex = startIndex + palavra.length

        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
            append(texto.substring(0, startIndex))
            withStyle(
                style = SpanStyle(
                    fontWeight = MaterialTheme.typography.body1.fontWeight,
                    fontStyle = MaterialTheme.typography.body1.fontStyle,
                    fontSize = MaterialTheme.typography.body1.fontSize * 1.2f
                )
            ) {
                append(texto.substring(startIndex, endIndex))
            }
            append(texto.substring(endIndex, texto.length))
        }
    }
    return resultado

    // Utilize o resultado como desejar, por exemplo, exibindo em um Text
    // Text(text = resultado.toAnnotatedString())
}

@Composable
internal fun TextSection(news: GameNewsState) {
    Column(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        val texto = "Gremlins: Secrets Of The Mogwai Trailer Hints At Gizmo’s Magical Origins"

        val textoFormatadoEmNegrito1 = formatarPalavraEmNegrito(texto = texto, palavra = "Trailer")
        Text(
            text = textoFormatadoEmNegrito1
        )

        val textoFormatadoEmNegrito =
            formatWordSearchedToBold(text = texto, word = "Trailer")
        Text(
            text = textoFormatadoEmNegrito
        )

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
