package com.example.gamenews.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Divider
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
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

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(bottom = 8.dp),
        shape = RoundedCornerShape(
            topStart = 0.dp,
            topEnd = 0.dp,
            bottomStart = 25.dp,
            bottomEnd = 25.dp
        ),
        elevation = 4.dp,
        color = colorResource(id = R.color.white)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp),
                text = "Advanced search",
                color = colorResource(id = R.color.game_news_title_color),
                fontSize = 19.dp.value.sp,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Ellipsis,
            )

            Row {
                // screen size

                SearchTextFieldComponent(
                    screenWidth = (screenWidth) / 2,
                    onValueChange = { }
                )

                Divider(
                    color = colorResource(id = R.color.game_news_try_again_button_color),
                    modifier = Modifier
                        .fillMaxHeight() // fill the max height
                        .width(1.dp)
                )

/*                Text(
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 18.dp),
                    text = "Items per page: ",
                    fontSize = dimensionResource(id = R.dimen.game_news_description_size).value.sp,
                    fontFamily = FontFamily(Typeface.SANS_SERIF),
                    overflow = TextOverflow.Ellipsis,
                )*/
                Counter()
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchTextFieldComponent(
    onValueChange: (String) -> Unit = {},
    screenWidth: Dp
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    OutlinedTextField(
        value = "Text",
        label = { Text(text = "Items per page") },
        placeholder = { Text(text = "Advanced search") },
        singleLine = true,
        onValueChange = { onValueChange(it) },
        shape = RoundedCornerShape(15),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
                focusManager.clearFocus()
            }
        ),
        modifier = Modifier
            .padding(top = 8.dp, start = 16.dp, end = 16.dp)
            .width(175.dp)
    )
}

@Preview
@Composable
fun AdvancedSearchComponentPreview() {
    AdvancedSearchComponent()
}
