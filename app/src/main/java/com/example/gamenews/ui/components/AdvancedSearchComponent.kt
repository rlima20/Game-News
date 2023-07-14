package com.example.gamenews.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gamenews.R

@Composable
fun AdvancedSearchComponent(
    onExitButtonClick: () -> Boolean,
    onSubmitButtonClicked: (Int, String) -> Unit,
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val quantifierState = remember { mutableStateOf(9) }
    val advancedSearchBarText = remember { mutableStateOf("") }

    if (onExitButtonClick()) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(id = R.dimen.game_news_advanced_search_height))
                .padding(bottom = 8.dp),
            elevation = 6.dp,
            shape = setRoundedShapeOnBottom(),
            color = colorResource(id = R.color.game_news_advanced_search_background_color),
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        modifier = Modifier
                            .padding(
                                start = 16.dp,
                                end = 16.dp,
                                top = 8.dp,
                            ),
                        text = "Advanced search",
                        color = colorResource(id = R.color.game_news_splash_activity_main_color),
                        fontSize = dimensionResource(id = R.dimen.game_news_advanced_search_title_size).value.sp,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.Bold,
                        overflow = TextOverflow.Ellipsis,
                    )
                    IconButton(
                        modifier = Modifier.size(32.dp),
                        onClick = {
                            onExitButtonClick()
                            quantifierState.value = 9
                            advancedSearchBarText.value = ""
                        },
                    ) {
                        Text(
                            modifier = Modifier.padding(end = 8.dp, top = 8.dp),
                            text = stringResource(id = R.string.game_news_exit_text),
                            color = colorResource(id = R.color.game_news_splash_activity_main_color),
                            fontSize = dimensionResource(id = R.dimen.game_news_advanced_search_title_size).value.sp,
                            fontStyle = FontStyle.Normal,
                            fontWeight = FontWeight.Bold,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }
                }

                SearchTextFieldComponent(
                    text = advancedSearchBarText.value,
                    onValueChange = { typedText -> advancedSearchBarText.value = typedText },
                )

                Column {
                    Text(
                        modifier = Modifier.padding(
                            start = 16.dp,
                            top = 8.dp,
                            end = 16.dp,
                        ),
                        text = "Items per page: ",
                        fontSize = dimensionResource(id = R.dimen.game_news_description_size).value.sp,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Quantifier(
                            width = setItemSize(
                                screenWidth = screenWidth,
                            ),
                            quantifier = quantifierState.value,
                            onQuantifierChange = { quantifierState.value = it },
                        )

                        SubmitButtonComponent(
                            onClick = {
                                onSubmitButtonClicked(
                                    quantifierState.value,
                                    advancedSearchBarText.value,
                                )
                            },
                            width = setItemSize(
                                screenWidth = screenWidth,
                            ),
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun setRoundedShapeOnBottom() = RoundedCornerShape(
    topStart = 0.dp,
    topEnd = 0.dp,
    bottomStart = 25.dp,
    bottomEnd = 25.dp,
)

private fun setItemSize(screenWidth: Int): Dp = ((screenWidth - 16) / 2).dp

@Preview
@Composable
fun AdvancedSearchComponentPreview() {
    AdvancedSearchComponent(
        onExitButtonClick = { false },
        onSubmitButtonClicked = { _, _ -> },
    )
}
