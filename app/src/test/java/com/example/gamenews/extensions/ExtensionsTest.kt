package com.example.gamenews.extensions

import io.kotlintest.shouldBe
import org.junit.Test

class ExtensionsTest {

    @Test
    fun `test remove spaces`() {
        // given
        val text = "This is a text"

        // when
        val textWithoutSpaces = text.removeSpaces()

        // then
        textWithoutSpaces shouldBe "Thisisatext"
    }
}
