package com.example.gamenews.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gamenews.R

@Composable
fun Counter() {
    Surface(
        modifier = Modifier
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
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.padding(start = 16.dp),
                text = "1",
                fontSize = 38.dp.value.sp,
                color = colorResource(id = R.color.game_news_title_color)
            )
            Column(
                horizontalAlignment = Alignment.End
            ) {
                Box {
                    IconButton(
                        onClick = { }
                    ) {
                        Icon(
                            modifier = Modifier.size(32.dp),
                            imageVector = Icons.Default.KeyboardArrowUp,
                            contentDescription = "Add",
                            tint = colorResource(id = R.color.game_news_title_color)
                        )
                    }
                }
                Box {
                    IconButton(
                        onClick = { }
                    ) {
                        Icon(
                            modifier = Modifier.size(32.dp),
                            imageVector = Icons.Default.KeyboardArrowDown,
                            contentDescription = "Add",
                            tint = colorResource(id = R.color.game_news_title_color)
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun CounterPreview() {
    Counter()
}
