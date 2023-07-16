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
    advancedSearchIconClickedValue: Boolean,
    advancedSearchState: Pair<Int, String>,
    onAdvancedSearchIconClicked: () -> Unit,
    onSubmitButtonClicked: (Int, String) -> Unit,
    onAdvancedSearchState: (Pair<Int, String>) -> Unit = {},
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val defautValue = Pair(9, "")

    if (advancedSearchIconClickedValue) {
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
                        text = stringResource(R.string.game_news_advanced_search_title),
                        color = colorResource(id = R.color.game_news_splash_activity_main_color),
                        fontSize = dimensionResource(id = R.dimen.game_news_advanced_search_title_size).value.sp,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.Bold,
                        overflow = TextOverflow.Ellipsis,
                    )
                    IconButton(
                        modifier = Modifier.size(32.dp),
                        onClick = {
                            onAdvancedSearchIconClicked()
                            onAdvancedSearchState(defautValue)
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
                    text = advancedSearchState.second,
                    onValueChange = { typedText ->
                        onAdvancedSearchState(Pair(advancedSearchState.first, typedText))
                    },
                )

                Column {
                    Text(
                        modifier = Modifier.padding(
                            start = 16.dp,
                            top = 8.dp,
                            end = 16.dp,
                        ),
                        text = stringResource(R.string.game_news_items_per_page),
                        fontSize = dimensionResource(id = R.dimen.game_news_description_size).value.sp,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Quantifier(
                            quantifier = advancedSearchState.first,
                            onQuantifierChange = {
                                onAdvancedSearchState(Pair(it, advancedSearchState.second))
                            },
                            width = setItemSize(
                                screenWidth = screenWidth,
                            ),
                        )

                        SubmitButtonComponent(
                            onClick = {
                                onSubmitButtonClicked(
                                    advancedSearchState.first,
                                    advancedSearchState.second,
                                )
                                onAdvancedSearchIconClicked()
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
        onSubmitButtonClicked = { _, _ -> },
        advancedSearchIconClickedValue = false,
        onAdvancedSearchIconClicked = {},
        advancedSearchState = Pair(1, ""),
        onAdvancedSearchState = {
            Pair(1, "")
        },
    )
}
