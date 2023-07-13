package com.example.gamenews.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gamenews.R

@Composable
fun AdvancedSearchComponent() {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    val quantifierState = remember { mutableStateOf(9) }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
            .padding(bottom = 8.dp),
        shape = RoundedCornerShape(
            topStart = 0.dp,
            topEnd = 0.dp,
            bottomStart = 25.dp,
            bottomEnd = 25.dp,
        ),
        elevation = 4.dp,
        color = colorResource(id = R.color.white),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp),
                text = "Advanced search",
                color = colorResource(id = R.color.game_news_splash_activity_main_color),
                fontSize = 19.dp.value.sp,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Ellipsis,
            )

            SearchTextFieldComponent(
                screenWidth = (screenWidth) / 2,
                onValueChange = { },
            )

            Column {
                Text(
                    modifier = Modifier.padding(start = 16.dp, top = 8.dp, end = 16.dp),
                    text = "Items per page: ",
                    fontSize = dimensionResource(id = R.dimen.game_news_description_size).value.sp,
                    overflow = TextOverflow.Ellipsis,
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Quantifier(
                        quantifier = quantifierState.value,
                        onQuantifierChange = { quantifierState.value = it },
                    )

                    SubmitButton()
                }
            }
        }
    }
}

@Composable
private fun SubmitButton() {
    Surface(
        modifier = Modifier
            .width(150.dp)
            .height(48.dp)
            .padding(start = 16.dp, top = 8.dp, end = 16.dp)
            .padding(bottom = 8.dp),
        shape = RoundedCornerShape(
            topStart = 25.dp,
            topEnd = 25.dp,
            bottomStart = 25.dp,
            bottomEnd = 25.dp,
        ),
        elevation = 4.dp,
        color = colorResource(id = R.color.white),
    ) {
        TextButton(
            modifier = Modifier
                .height(38.dp)
                .width(400.dp)
                .background(colorResource(id = R.color.game_news_splash_activity_main_color)),
            onClick = { /*TODO*/ },
            shape = RoundedCornerShape(
                topStart = 5.dp,
                topEnd = 5.dp,
                bottomStart = 5.dp,
                bottomEnd = 5.dp,
            ),
        ) {
            Text(
                text = "Submit",
                fontSize = 12.dp.value.sp,
                overflow = TextOverflow.Ellipsis,
                color = colorResource(id = R.color.white),
            )
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchTextFieldComponent(
    onValueChange: (String) -> Unit = {},
    screenWidth: Dp,
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

@Preview
@Composable
fun AdvancedSearchComponentPreview() {
    AdvancedSearchComponent()
}
