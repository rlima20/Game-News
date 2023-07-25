package com.example.gamenews.ui.components

import android.annotation.SuppressLint
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gamenews.R

@Composable
internal fun Quantifier(
    width: Dp,
    quantifier: Int,
    onQuantifierChange: (Int) -> Unit,
) {
    val isLeftIconEnabled = quantifier > 1
    val isRightIconEnabled = quantifier < 9

    Surface(
        modifier = Modifier
            .height(48.dp)
            .width(width)
            .padding(
                start = 16.dp,
                top = 8.dp,
                end = 16.dp,
            )
            .padding(bottom = 8.dp),
        elevation = 4.dp,
        shape = setRoundedCornersShape(),
        color = colorResource(id = R.color.game_news_white_color),
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
                    contentDescription = stringResource(R.string.game_news_quantifier_decrease),
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
                    contentDescription = stringResource(R.string.game_news_quantifier_increase),
                    tint = SetIconButtonColor(isRightIconEnabled),
                )
            }
        }
    }
}

private fun setRoundedCornersShape(): RoundedCornerShape =
    RoundedCornerShape(
        topStart = 25.dp,
        topEnd = 25.dp,
        bottomStart = 25.dp,
        bottomEnd = 25.dp,
    )

@SuppressLint("ComposableNaming")
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
        48.dp,
        9,
    ) {}
}
