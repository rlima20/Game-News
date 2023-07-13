package com.example.gamenews.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gamenews.R

@Composable
internal fun Quantifier(
    modifier: Modifier = Modifier,
    quantifier: Int,
    onQuantifierChange: (Int) -> Unit,
) {
    val isLeftIconEnabled = quantifier > 1
    val isRightIconEnabled = quantifier < 9

    Surface(
        modifier = Modifier
            .height(48.dp)
            .width(138.dp)
            .padding(
                start = 16.dp,
                top = 8.dp,
                end = 16.dp,
            )
            .padding(bottom = 8.dp),
        elevation = 4.dp,
        shape = setRoundedCornersShape(),
        color = colorResource(id = R.color.white),
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            IconButton(
                enabled = isLeftIconEnabled,
                onClick = { onQuantifierChange(quantifier - 1) },
            ) {
                Icon(
                    modifier = Modifier.size(18.dp),
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = "Decrease",
                    tint = SetIconButtonColor(isLeftIconEnabled),
                )
            }

            Text(
                modifier = Modifier.padding(top = 4.dp),
                text = quantifier.toString(),
                fontSize = 18.dp.value.sp,
                color = colorResource(id = R.color.game_news_title_color),
            )

            IconButton(
                enabled = isRightIconEnabled,
                onClick = { onQuantifierChange(quantifier + 1) },
            ) {
                Icon(
                    modifier = Modifier.size(18.dp),
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = "Increase",
                    tint = SetIconButtonColor(isRightIconEnabled),
                )
            }
        }
    }
}

fun setRoundedCornersShape(): RoundedCornerShape =
    RoundedCornerShape(
        topStart = 25.dp,
        topEnd = 25.dp,
        bottomStart = 25.dp,
        bottomEnd = 25.dp,
    )

@Composable
private fun SetIconButtonColor(condition: Boolean): Color =
    if (condition) {
        colorResource(id = R.color.game_news_quantifier_icon_enabled_color)
    } else {
        colorResource(id = R.color.game_news_quantifier_icon_disabled_color)
    }

@Preview
@Composable
private fun QuantifierPreview() {
    Quantifier(
        Modifier,
        9,
        {},
    )
}
