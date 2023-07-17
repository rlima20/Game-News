package com.example.gamenews.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gamenews.R

@OptIn(ExperimentalComposeUiApi::class)
@Composable
internal fun SearchBarComponent(
    text: String = "",
    onValueChange: (String) -> Unit = {},
    onCloseIconClicked: () -> Unit = {},
    onSearched: () -> Unit = {},
    onAdvancedSearchIconClicked: () -> Unit,
    advancedSearchIconClickedValue: Boolean,
    shoudActivateAdvancedSearch: Boolean,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    val isVisible = remember { mutableStateOf(true) }

    if (advancedSearchIconClickedValue) {
        OutlinedTextField(
            value = text,
            label = { Text(text = "Keyword") },
            placeholder = { Text(text = "Filter") },
            singleLine = true,
            onValueChange = { onValueChange(it) },
            shape = RoundedCornerShape(100),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                    focusManager.clearFocus()
                    onSearched()
                },
            ),
            modifier = Modifier
                .padding(top = 8.dp, start = 16.dp, end = 16.dp)
                .fillMaxWidth(),
            leadingIcon = {
                Icon(
                    Icons.Default.Search,
                    modifier = Modifier.padding(start = 8.dp),
                    contentDescription = null,
                )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = colorResource(id = R.color.game_news_white_color),
                focusedBorderColor = colorResource(id = R.color.game_news_splash_activity_main_color),
                unfocusedBorderColor = colorResource(id = R.color.game_news_quantifier_icon_disabled_color),
                focusedLabelColor = colorResource(id = R.color.game_news_splash_activity_main_color),
            ),
            trailingIcon = {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    if (text.isNotEmpty()) {
                        IconButton(
                            modifier = Modifier
                                .padding(
                                    bottom = 6.dp,
                                    end = 8.dp,
                                ),
                            onClick = {
                                onValueChange("")
                                onCloseIconClicked()
                                keyboardController?.hide()
                                focusManager.clearFocus()
                            },
                        ) {
                            Text(
                                text = stringResource(id = R.string.game_news_exit_text),
                                fontSize = 22.sp,
                            )
                        }
                    } else {
                        if (shoudActivateAdvancedSearch) {
                            IconButton(
                                onClick = { onAdvancedSearchIconClicked() },
                            ) {
                                Image(
                                    modifier = Modifier
                                        .size(48.dp)
                                        .padding(end = 16.dp),
                                    painter = painterResource(R.drawable.game_news_advanced_search_icon),
                                    contentDescription = null,
                                )
                            }
                        }
                    }
                }
            },
        )
    }
}

@Preview
@Composable
fun Preview() {
    SearchBarComponent(
        "Abc",
        {},
        {},
        {},
        { },
        false,
        false
    )
    Icon(
        imageVector = ImageVector.vectorResource(id = R.drawable.game_news_advanced_search_icon),
        contentDescription = null,
    )
}
