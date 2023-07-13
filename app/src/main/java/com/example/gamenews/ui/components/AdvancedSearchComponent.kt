package com.example.gamenews.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
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
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val quantifierState = remember { mutableStateOf(9) }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(178.dp)
            .padding(bottom = 8.dp),
        shape = RoundedCornerShape(
            topStart = 0.dp,
            topEnd = 0.dp,
            bottomStart = 25.dp,
            bottomEnd = 25.dp,
        ),
        elevation = 6.dp,
        color = colorResource(id = R.color.white),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            Text(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 8.dp),
                text = "Advanced search",
                color = colorResource(id = R.color.game_news_splash_activity_main_color),
                fontSize = 19.dp.value.sp,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Ellipsis,
            )

            SearchTextFieldComponent(
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
                        setItemSize(
                            screenWidth = screenWidth,
                        ),
                    )
                }
            }
        }
    }
}

private fun setItemSize(screenWidth: Int): Dp = (
    (screenWidth - 16) / 2
    ).dp

@Preview
@Composable
fun AdvancedSearchComponentPreview() {
    AdvancedSearchComponent()
}
