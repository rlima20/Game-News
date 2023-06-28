package com.example.gamenews.ui.components

import android.graphics.Typeface
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gamenews.R
import com.example.gamenews.extensions.getSpanStyles
import com.example.gamenews.model.GameNewsState
import java.util.Locale

@Composable
internal fun TextSection(
    searchedWord: String,
    gameNewsState: GameNewsState
) {
    Column(
        modifier = Modifier
            .testTag("TEXT_SECTION_TEST_TAG")
            .padding(start = 16.dp, end = 16.dp, top = 16.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Text(
            modifier = Modifier.testTag("TEXT_SECTION_TITLE_TEST_TAG"),
            text = AnnotatedString(
                text = gameNewsState.title,
                spanStyles = getSpanStyles(
                    mainText = gameNewsState.title.lowercase(Locale.ROOT),
                    searchedWord = searchedWord.lowercase(Locale.ROOT)
                )
            ),
            color = colorResource(id = R.color.game_news_title_color),
            fontSize = dimensionResource(id = R.dimen.game_news_title_size).value.sp,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Bold,
            overflow = TextOverflow.Ellipsis,
            maxLines = 2
        )

        Text(
            modifier = Modifier.testTag("TEXT_SECTION_DATE_TEST_TAG"),
            text = gameNewsState.date,
            color = colorResource(id = R.color.game_news_title_color),
            fontSize = dimensionResource(id = R.dimen.game_news_date_size).value.sp,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Bold,
            maxLines = 1
        )
    }

    Column(
        modifier = Modifier.padding(start = 16.dp, bottom = 16.dp, end = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            modifier = Modifier.testTag("TEXT_SECTION_DESCRIPTION_TEST_TAG"),
            text = AnnotatedString(
                text = gameNewsState.description,
                spanStyles = getSpanStyles(
                    mainText = gameNewsState.description.lowercase(Locale.ROOT),
                    searchedWord = searchedWord.lowercase(Locale.ROOT)
                )
            ),
            fontSize = dimensionResource(id = R.dimen.game_news_description_size).value.sp,
            fontFamily = FontFamily(Typeface.SANS_SERIF),
            overflow = TextOverflow.Ellipsis,
            maxLines = 3
        )
        Text(
            modifier = Modifier
                .testTag("TEXT_SECTION_LINK_TEST_TAG")
                .clickable(
                    enabled = true,
                    onClickLabel = gameNewsState.link,
                    role = Role.Button,
                    onClick = {},
                ),
            text = gameNewsState.link,
            textDecoration = TextDecoration.Underline,
            color = colorResource(id = R.color.game_news_blue_700)
        )
    }
}
