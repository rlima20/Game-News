package com.example.gamenews.usecases

import com.example.gamenews.CoroutineTestRule
import com.example.gamenews.core.Either
import com.example.gamenews.model.GameNewsDTO
import com.example.gamenews.provider.local.listOfNewsDTO
import com.example.gamenews.provider.remote.GameNewsService
import com.example.gamenews.repository.GameNewsRepository
import com.example.gamenews.repository.GameNewsRepositoryImpl
import io.kotlintest.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
class GameNewsUseCaseImplTest : CoroutineTestRule() {

    private val gameNewsService: GameNewsService = mockk(relaxed = true)
    private val gameNewsRepository: GameNewsRepository = mockk(relaxed = true)

    @get:Rule
    @ExperimentalCoroutinesApi
    val coroutineTestRule = CoroutineTestRule()

    @Test
    fun `test useCase invoke method returns an Either success with an emptyList`() = runBlocking {
        // Given
        coEvery { gameNewsRepository.getAllGameNews() } returns flow {
            emit(emptyList())
        }

        // When
        val result = GameNewsUseCaseImpl(gameNewsRepository).invoke()

        // Then
        result.collect(
            collector = {
                it shouldBe Either.Success(emptyList())
            }
        )
    }

    @Test
    fun `test useCase invoke method returns an Either success with a listOfNewsDTO`() =
        runBlocking {
            // Given
            coEvery { gameNewsRepository.getAllGameNews() } returns flow {
                emit(listOfNewsDTO)
            }

            // When
            val result = GameNewsUseCaseImpl(gameNewsRepository).invoke()

            // Then
            result.collect(
                collector = {
                    it shouldBe Either.Success(listOfNewsDTO)
                }
            )
        }

    @Test
    fun `test useCase invoke method returns an Either success with a null list`() =
        runBlocking {
            // Given
            coEvery { gameNewsRepository.getAllGameNews() } returns flow {
                emit(null)
            }

            // When
            val result = GameNewsUseCaseImpl(gameNewsRepository).invoke()

            // Then
            result.collect(
                collector = {
                    it shouldBe Either.Success(null)
                }
            )
        }

    // todo - retomar daqui
/*    @Test
    fun `test useCase invoke method returns an Either error`() =
        runBlocking {
            // Given
            val response: Response<List<GameNewsDTO>>? = Response.error(
                502,
                ResponseBody.create(null, "")
            )

            coEvery { gameNewsService.getAllGameNews() } returns response

            // When
            val result = GameNewsUseCaseImpl(gameNewsRepository).invoke()

            // Then
            result.collect(
                collector = {
                    it shouldBe Either.Failure(Exception())
                }
            )
        }*/
}
