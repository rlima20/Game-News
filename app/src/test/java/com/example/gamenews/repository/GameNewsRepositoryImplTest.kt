package com.example.gamenews.repository

import com.example.gamenews.CoroutineTestRule
import com.example.gamenews.provider.local.listOfNewsDTO
import com.example.gamenews.provider.remote.GameNewsService
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
internal class GameNewsRepositoryImplTest : CoroutineTestRule() {

    private val gameNewsService: GameNewsService = mockk(relaxed = true)

    @get:Rule
    @ExperimentalCoroutinesApi
    val coroutineTestRule = CoroutineTestRule()

    @Test
    fun getAllGameNews() {
        // GIVEN
        // WHEN
        coEvery { gameNewsService.getAllGameNews() } returns Response.success(listOfNewsDTO)

        // THEN
    }

    @Test
    fun getAllGameNewsLocal() {
        // GIVEN
        // WHEN
        // THEN
    }
}
