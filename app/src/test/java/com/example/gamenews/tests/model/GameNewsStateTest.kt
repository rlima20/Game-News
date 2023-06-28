package com.example.gamenews.tests.model

import com.example.gamenews.model.GameNewsState
import io.kotlintest.shouldBe
import org.junit.Test

class GameNewsStateTest {

    @Test
    fun `test getters and setters`() {
        // GIVEN
        val gameNewsState = GameNewsState(
            title = "title",
            date = "date",
            description = "description",
            image = "image",
            link = "link"
        )
        // WHEN
        val title = gameNewsState.title
        val date = gameNewsState.date
        val description = gameNewsState.description
        val image = gameNewsState.image
        val link = gameNewsState.link

        // THEN
        title shouldBe "title"
        date shouldBe "date"
        description shouldBe "description"
        image shouldBe "image"
        link shouldBe "link"
    }
}
