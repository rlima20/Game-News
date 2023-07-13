package com.example.gamenews.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchTextFieldComponent(
    onValueChange: (String) -> Unit = {},
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    OutlinedTextField(
        value = "",
        label = { Text(text = "Keyword") },
        placeholder = { Text(text = "Advanced search") },
        singleLine = true,
        onValueChange = { onValueChange(it) },
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
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp)
            .height(50.dp)
            .fillMaxWidth(),
    )
}
