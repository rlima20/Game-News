package com.example.gamenews.viewmodel

import com.example.gamenews.core.Either
import com.example.gamenews.usecases.GameNewsUseCase
import io.kotlintest.shouldBe
import io.mockk.ConstantAnswer
import io.mockk.MockKAnnotations
import io.mockk.MockKStubScope
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class GameNewsViewModelTest {

    private var viewModelUseCase: GameNewsUseCase = mockk(relaxed = true)
    lateinit var gameNewsViewModel: GameNewsViewModel

    @get:Rule
    @ExperimentalCoroutinesApi
    val coroutineTestRule = CoroutineTestRule()

    @Test
    fun clearFilteredListOfGameNews() {
        // GIVEN
        gameNewsViewModel = GameNewsViewModel(viewModelUseCase)

        // WHEN
        gameNewsViewModel.clearFilteredListOfGameNews()

        // THEN
        // uistate null e success
        gameNewsViewModel.uiStateFiltered.value shouldBe null
    }

    @Test
    fun clearFilteredListOfGameNews2() {
        // GIVEN
        gameNewsViewModel = GameNewsViewModel(viewModelUseCase)

        // WHEN
        gameNewsViewModel.setImageDialog(true)

        // THEN
        // uistate null e success
        gameNewsViewModel.imageDialog.value shouldBe true
    }

    @Test
    fun getShouldSearchFromAPI() {
    }

    @Test
    fun getUiState() {
    }

    @Test
    fun getUiStateFiltered() {
    }

    @Test
    fun getRequestStatus() {
    }

    @Test
    fun getImageDialog() {
    }

    @Test
    fun filterListOfGameNews() {
    }

    @Test
    fun setImageDialog() {
    }

    @Test
    fun getAsyncImage() {
    }

/*    @Test
    fun `test fetch data`() = runBlocking() {
        // GIVEN
        gameNewsViewModel = GameNewsViewModel(viewModelUseCase)

        coEvery { viewModelUseCase.invoke() } emmits Either.Success(null)

        // WHEN
        gameNewsViewModel.fetchData()

        // THEN
        // uistate null e success
        gameNewsViewModel.requestStatus.value shouldBe null
    }*/
}

fun Any.initMockKAnnotations() {
    MockKAnnotations.init(this, relaxUnitFun = true)
}

infix fun <T, B> MockKStubScope<Flow<T>, B>.emmits(emitValue: T) =
    answers(ConstantAnswer(flow { emit(emitValue) }))

infix fun <T, B> MockKStubScope<Flow<T>, B>.emmits(emitValues: List<T>) = answers(
    ConstantAnswer(
        flow {
            emitValues.forEach { value ->
                emit(value)
            }
        },
    ),
)

@ExperimentalCoroutinesApi
class CoroutineTestRule(
    private val dispatcher: TestDispatcher = UnconfinedTestDispatcher(),
) : TestWatcher() {

    override fun starting(description: Description) {
        super.starting(description)
        Dispatchers.setMain(dispatcher)
    }

    override fun finished(description: Description) {
        super.finished(description)
        Dispatchers.resetMain()
    }
}
