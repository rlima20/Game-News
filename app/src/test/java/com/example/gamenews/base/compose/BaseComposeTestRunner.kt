package com.example.gamenews.base.compose

import androidx.compose.ui.test.junit4.createComposeRule
import com.example.gamenews.base.RobolectricTestRunner
import org.junit.Rule
import org.robolectric.annotation.Config

@Config(instrumentedPackages = ["androidx.loader.content"])
abstract class BaseComposeTestRunner : RobolectricTestRunner() {

    @get:Rule
    val composeTestRule = createComposeRule()
}
