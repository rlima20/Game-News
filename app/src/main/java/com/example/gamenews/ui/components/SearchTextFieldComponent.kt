package com.example.gamenews.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gamenews.R

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchTextFieldComponent(
    text: String = "",
    onValueChange: (String) -> Unit = {},
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    OutlinedTextField(
        value = text,
        onValueChange = { onValueChange(it) },
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp)
            .fillMaxWidth(),
        label = { Text(text = "Keyword") },
        placeholder = { Text(text = "Advanced search") },
        singleLine = true,
        shape = RoundedCornerShape(50),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
                focusManager.clearFocus()
            },
        ),
        leadingIcon = {
            Icon(
                Icons.Default.Search,
                modifier = Modifier.padding(start = 8.dp),
                contentDescription = null,
            )
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            backgroundColor = colorResource(id = R.color.white),
            focusedBorderColor = colorResource(id = R.color.game_news_splash_activity_main_color),
            unfocusedBorderColor = colorResource(id = R.color.game_news_quantifier_icon_disabled_color),
            focusedLabelColor = colorResource(id = R.color.game_news_splash_activity_main_color),
        ),
    )
}

@Preview
@Composable
fun SearchTextFieldComponentPreview() {
    SearchTextFieldComponent()
}
