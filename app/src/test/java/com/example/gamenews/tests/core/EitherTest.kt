package com.example.gamenews.tests.core

import com.example.gamenews.provider.remote.Either
import com.example.gamenews.global.CoroutineTestRule
import com.example.gamenews.repository.GameNewsRepository
import com.example.gamenews.usecases.GameNewsUseCaseImpl
import io.kotlintest.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class EitherTest : CoroutineTestRule() {

    private val gameNewsRepository: GameNewsRepository = mockk(relaxed = true)
    private val gameNewsUseCaseImpl: GameNewsUseCaseImpl = mockk(relaxed = true)

    @get:Rule
    @ExperimentalCoroutinesApi
    val coroutineTestRule = CoroutineTestRule()

    @Test
    fun `test useCase invoke method returns an Either Success`() =
        runBlocking {

            // When
            coEvery { gameNewsUseCaseImpl.invoke() } returns flow {
                Either.Success(Throwable())
            }

            // Then
            GameNewsUseCaseImpl(gameNewsRepository).invoke().collect(
                collector = {
                    it.isSuccess shouldBe true
                }
            )
        }

    @Test
    fun `test useCase invoke method returns an Either failure`() =
        runBlocking {

            // When
            coEvery { gameNewsUseCaseImpl.invoke() } returns flow {
                Either.Failure(Throwable())
            }

            // Then
            GameNewsUseCaseImpl(gameNewsRepository).invoke().collect(
                collector = {
                    it.isFailure shouldBe true
                }
            )
        }
}
