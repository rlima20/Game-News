package com.example.gamenews.repository

import com.example.gamenews.CoroutineTestRule
import com.example.gamenews.provider.local.listOfNews
import com.example.gamenews.provider.local.listOfNewsDTO
import com.example.gamenews.provider.remote.GameNewsService
import io.kotlintest.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
internal class GameNewsRepositoryImplTest : CoroutineTestRule() {

    private val gameNewsService: GameNewsService = mockk(relaxed = true)

    @get:Rule
    @ExperimentalCoroutinesApi
    val coroutineTestRule = CoroutineTestRule()

    //@Test
/*    fun `test When request is success and it returns a listOfNewsDTO`() = runBlocking {
        // GIVEN
        val gameNewsRepositoryImpl = GameNewsRepositoryImpl(gameNewsService)
        coEvery { gameNewsService.getAllGameNews() } returns Response.success(listOfNewsDTO)

        // WHEN
        val result = gameNewsRepositoryImpl.getAllGameNews()

        // THEN
        result.collect(
            collector = {
                it shouldBe listOfNewsDTO
            }
        )
    }

    @Test
    fun `test When request returns a null value then the repository returns an emptyList`() =
        runBlocking {
            // GIVEN
            val gameNewsRepositoryImpl = GameNewsRepositoryImpl(gameNewsService)
            coEvery { gameNewsService.getAllGameNews() } returns null

            // WHEN
            val result = gameNewsRepositoryImpl.getAllGameNews()

            // THEN
            result.collect(
                collector = {
                    it shouldBe listOf()
                }
            )
        }*/

    @Test
    fun getAllGameNewsLocal() {
        // GIVEN
        val gameNewsRepositoryImpl = GameNewsRepositoryImpl(gameNewsService)

        // WHEN
        val result = gameNewsRepositoryImpl.getAllGameNewsLocal()

        // THEN
        result shouldBe listOfNews
    }
}
