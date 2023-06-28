package com.example.gamenews.tests.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithText
import com.example.gamenews.base.compose.BaseComposeTestRunner
import com.example.gamenews.model.GameNewsState
import com.example.gamenews.ui.components.TextSection
import org.junit.Test

class TextSectionTest : BaseComposeTestRunner() {

    @Test
    fun testTextSection() {
        val searchedWord = "example"

        val gameNewsState = GameNewsState(
            title = "Example Title",
            date = "June 27, 2023",
            description = "Example description",
            link = "https://example.com"
        )

        composeTestRule.setContent {
            TextSection(searchedWord, gameNewsState)
        }

        // Assert the title text
        composeTestRule.onNodeWithText("Example Title").assertIsDisplayed()
    }
}
