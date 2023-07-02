package com.example.gamenews.tests.viewmodel

import coil.request.ImageRequest
import coil.size.Size
import com.example.gamenews.global.CoroutineTestRule
import com.example.gamenews.provider.remote.Either
import com.example.gamenews.global.emmits
import com.example.gamenews.model.States
import com.example.gamenews.provider.local.listOfNewsDTO
import com.example.gamenews.usecases.GameNewsUseCase
import com.example.gamenews.viewmodel.GameNewsViewModel
import io.kotlintest.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GameNewsViewModelTest : CoroutineTestRule() {

    private var viewModelUseCase: GameNewsUseCase = mockk(relaxed = true)
    lateinit var gameNewsViewModel: GameNewsViewModel

    @get:Rule
    @ExperimentalCoroutinesApi
    val coroutineTestRule = CoroutineTestRule()

    @Test
    fun `Test when useCase returns a failure the requestStatus should be StatesError`() =
        runBlocking {
            // GIVEN
            gameNewsViewModel = GameNewsViewModel(viewModelUseCase)

            coEvery { viewModelUseCase.invoke() } emmits Either.Failure(Exception())

            // WHEN
            gameNewsViewModel.fetchData()

            // THEN
            gameNewsViewModel.uiState.value shouldBe null
            gameNewsViewModel.requestStatus.value shouldBe States.ERROR
        }

    @Test
    fun `Test when useCase returns a success with empty result then uiState should be empty`() =
        runBlocking {
            // GIVEN
            gameNewsViewModel = GameNewsViewModel(viewModelUseCase)

            coEvery { viewModelUseCase.invoke() } emmits Either.Success(emptyList())

            // WHEN
            gameNewsViewModel.fetchData()

            // THEN
            gameNewsViewModel.uiState.value shouldBe emptyList()
            gameNewsViewModel.requestStatus.value shouldBe States.SUCCESS
        }

    @Test
    fun `Test when useCase returns a success with null result then uiState should be null`() =
        runBlocking {
            // GIVEN
            gameNewsViewModel = GameNewsViewModel(viewModelUseCase)

            coEvery { viewModelUseCase.invoke() } emmits Either.Success(null)

            // WHEN
            gameNewsViewModel.fetchData()

            // THEN
            gameNewsViewModel.uiState.value shouldBe null
            gameNewsViewModel.requestStatus.value shouldBe States.ERROR
        }

    @Test
    fun `Test uiStateFiltered value should be null when it is cleared`() {
        // GIVEN
        gameNewsViewModel = GameNewsViewModel(viewModelUseCase)

        // WHEN
        gameNewsViewModel.clearFilteredListOfGameNews()

        // THEN
        gameNewsViewModel.uiStateFiltered.value shouldBe null
    }

    @Test
    fun `Test when setImageDialog receives true it should return true`() {
        // GIVEN
        gameNewsViewModel = GameNewsViewModel(viewModelUseCase)

        // WHEN
        gameNewsViewModel.setImageDialog(true)

        // THEN
        gameNewsViewModel.imageDialog.value shouldBe true
    }

    @Test
    fun `Test when setImageDialog receives false it should return false`() {
        // GIVEN
        gameNewsViewModel = GameNewsViewModel(viewModelUseCase)

        // WHEN
        gameNewsViewModel.setImageDialog(false)

        // THEN
        gameNewsViewModel.imageDialog.value shouldBe false
    }

    @Test
    fun `test when getAsyncImage is called then an ImageRequest should be returned`() {
        // GIVEN
        gameNewsViewModel = GameNewsViewModel(viewModelUseCase)

        // WHEN
        val imageRequest = ImageRequest.Builder(context = mockk())
            .data("https://www.google.com")
            .size(Size.ORIGINAL)
            .crossfade(true)
            .build()

        // THEN
        gameNewsViewModel.getAsyncImage(
            mockk(), "https://www.google.com"
        ).data shouldBe imageRequest.data
    }

    // Fazer esses testes na View
    @Test
    fun `test when click on image then the image dialog is shown`() {
        // GIVEN
        gameNewsViewModel = GameNewsViewModel(viewModelUseCase)
        // Adicionar test tags aos componentes
        // quando ocorre o click em uma imagem o método setImageDialog é chamado. Nesse próprio
        // onCLick ocorre a mudança pra true do valor de imageDialog.
        // então o valor de imageDialog deve ser true
    }

    @Test
    fun `test when click on image then the image dialog is not shown`() {
        // GIVEN
        gameNewsViewModel = GameNewsViewModel(viewModelUseCase)
        // Adicionar test tags aos componentes
        // quando ocorre o click em uma imagem o método setImageDialog é chamado. Nesse próprio
        // onCLick ocorre a mudança pra true do valor de imageDialog.
        // então o valor de imageDialog deve ser true
    }

    @Test
    fun `test when there is an item filtered then the uiStateFiltered size should be 1`() {
        // GIVEN
        gameNewsViewModel = GameNewsViewModel(viewModelUseCase)
        coEvery { viewModelUseCase.invoke() } emmits Either.Success(listOfNewsDTO)

        // WHEN
        gameNewsViewModel.fetchData()
        gameNewsViewModel.filterListOfGameNews("Gremlins")

        // THEN
        gameNewsViewModel.uiStateFiltered.value?.size shouldBe 1
    }

    @Test
    fun `test when there is no item filtered then the uiStateFiltered should be null`() {
        // GIVEN
        gameNewsViewModel = GameNewsViewModel(viewModelUseCase)
        coEvery { viewModelUseCase.invoke() } emmits Either.Success(listOfNewsDTO)

        // WHEN
        gameNewsViewModel.fetchData()
        gameNewsViewModel.filterListOfGameNews("noword")

        // THEN
        gameNewsViewModel.uiStateFiltered.value shouldBe emptyList()
    }

    @Test
    fun `test when uiState is null then the uiStateFiltered should be null`() {
        // GIVEN
        gameNewsViewModel = GameNewsViewModel(viewModelUseCase)
        coEvery { viewModelUseCase.invoke() } emmits Either.Success(null)

        // WHEN
        gameNewsViewModel.fetchData()
        gameNewsViewModel.filterListOfGameNews("noword")

        // THEN
        gameNewsViewModel.uiStateFiltered.value shouldBe emptyList()
    }
}
