package com.example.gamenews.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
internal fun SearchBarComponent(
    text: String = "",
    onValueChange: (String) -> Unit = {},
    onCloseIconClicked: () -> Unit = {},
    onSearched: () -> Unit = {},
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        modifier = Modifier
            .padding(top = 8.dp, start = 16.dp, end = 16.dp)
            .fillMaxWidth(),
        value = text,
        onValueChange = { onValueChange(it) },
        shape = RoundedCornerShape(100),
        leadingIcon = {
            Icon(
                Icons.Default.Search,
                modifier = Modifier.padding(start = 8.dp),
                contentDescription = null
            )
        },
        trailingIcon = {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (text.isNotEmpty()) {
                    IconButton(
                        modifier = Modifier
                            .padding(
                                bottom = 6.dp,
                                end = 8.dp
                            ),
                        onClick = {
                            onValueChange("")
                            onCloseIconClicked()
                            keyboardController?.hide()
                            focusManager.clearFocus()
                        }
                    ) {
                        Text(
                            text = "x",
                            fontSize = 22.sp
                        )
                    }
                }
            }
        },
        label = { Text(text = "Keyword") },
        placeholder = { Text(text = "Filter") },
        singleLine = true,
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
                focusManager.clearFocus()
                onSearched()
            }
        )
    )
}

@Preview
@Composable
fun Preview() {
    SearchBarComponent(
        "Abc",
        {},
        {},
        {}
    )
}
