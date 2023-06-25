package com.example.gamenews.repository

import com.example.gamenews.CoroutineTestRule
import com.example.gamenews.model.GameNewsDTO
import com.example.gamenews.provider.local.listOfNews
import com.example.gamenews.provider.local.listOfNewsDTO
import com.example.gamenews.provider.remote.GameNewsService
import io.kotlintest.shouldBe
import io.kotlintest.shouldNotBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
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
    fun `test When request is success and it returns a listOfNewsDTO`() = runBlocking {
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
    fun `test When request is error and it returns a listOfNewsDTO 404`() = runBlocking {
        // GIVEN
        val response: Response<List<GameNewsDTO>>? = Response.error(
            404,
            ResponseBody.create(null, "")
        )
        val gameNewsRepositoryImpl = GameNewsRepositoryImpl(gameNewsService)
        coEvery { gameNewsService.getAllGameNews() } returns response

        // WHEN
        val result = gameNewsRepositoryImpl.getAllGameNews()

        // THEN
        result.collect(
            collector = {
                it shouldBe null
            }
        )
    }

    @Test
    fun `test When request is success and it returns a listOfNewsDTO 200`() = runBlocking {
        // GIVEN
        val response: Response<List<GameNewsDTO>>? = Response.success(listOfNewsDTO)
        val gameNewsRepositoryImpl = GameNewsRepositoryImpl(gameNewsService)
        coEvery { gameNewsService.getAllGameNews() } returns response

        // WHEN
        val result = gameNewsRepositoryImpl.getAllGameNews()

        // THEN
        result.collect(
            collector = {
                it.shouldBe(listOfNewsDTO)
                it shouldNotBe null
            }
        )
    }

    @Test
    fun `test When request is error and it returns a listOfNewsDTO 403`() = runBlocking {
        // GIVEN
        val response: Response<List<GameNewsDTO>>? = Response.error(
            403,
            ResponseBody.create(null, "")
        )
        val gameNewsRepositoryImpl = GameNewsRepositoryImpl(gameNewsService)
        coEvery { gameNewsService.getAllGameNews() } returns response

        // WHEN
        val result = gameNewsRepositoryImpl.getAllGameNews()

        // THEN
        result.collect(
            collector = {
                it shouldBe null
            }
        )
    }

    @Test
    fun `test When request is error and it returns a listOfNewsDTO 501`() = runBlocking {
        // GIVEN
        val response: Response<List<GameNewsDTO>>? = Response.error(
            501,
            ResponseBody.create(null, "")
        )
        val gameNewsRepositoryImpl = GameNewsRepositoryImpl(gameNewsService)
        coEvery { gameNewsService.getAllGameNews() } returns response

        // WHEN
        val result = gameNewsRepositoryImpl.getAllGameNews()

        // THEN
        result.collect(
            collector = {
                it shouldBe null
            }
        )
    }

    @Test
    fun `test When request is error and it returns a listOfNewsDTO 500`() = runBlocking {
        // GIVEN
        val response: Response<List<GameNewsDTO>>? = Response.error(
            500,
            ResponseBody.create(null, "")
        )
        val gameNewsRepositoryImpl = GameNewsRepositoryImpl(gameNewsService)
        coEvery { gameNewsService.getAllGameNews() } returns response

        // WHEN
        val result = gameNewsRepositoryImpl.getAllGameNews()

        // THEN
        result.collect(
            collector = {
                it shouldBe null
            }
        )
    }

    @Test
    fun `test When request is error and it returns a listOfNewsDTO 502`() = runBlocking {
        // GIVEN
        val response: Response<List<GameNewsDTO>>? = Response.error(
            502,
            ResponseBody.create(null, "")
        )
        val gameNewsRepositoryImpl = GameNewsRepositoryImpl(gameNewsService)
        coEvery { gameNewsService.getAllGameNews() } returns response

        // WHEN
        val result = gameNewsRepositoryImpl.getAllGameNews()

        // THEN
        result.collect(
            collector = {
                it shouldBe null
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
        }

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
