package com.example.gamenews.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.request.ImageRequest
import coil.size.Size
import com.example.gamenews.model.GameNewsDTO
import com.example.gamenews.model.GameNewsState
import com.example.gamenews.model.States
import com.example.gamenews.usecases.GameNewsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@Suppress("DEPRECATION")
class GameNewsViewModel(
    private val gameNewsUseCase: GameNewsUseCase
) : ViewModel() {

    private val _requestState = MutableStateFlow(States.LOADING)
    val requestState: StateFlow<States>
        get() = _requestState

    private val _uiState = MutableStateFlow(mutableListOf<GameNewsState>())
    val uiState: StateFlow<List<GameNewsState>> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            fetchData()
        }
    }

    private suspend fun fetchData() {
        _requestState.value = States.LOADING
        viewModelScope.launch {
            gameNewsUseCase.invoke().collect { result ->
                result.either(
                    onSuccess = { updateGameNewsState(toMap(it)) },
                    onFailure = { updateRequestErrorState() }
                )
            }
        }
    }

    private fun toMap(listOfGameNewsDTO: List<GameNewsDTO>): MutableList<GameNewsState> {
        val listOfGameNewStates: MutableList<GameNewsState> = mutableListOf()

        listOfGameNewsDTO.forEach {
            listOfGameNewStates.add(
                GameNewsState(
                    title = it.title,
                    date = formatDateToDateNews(it.date),
                    description = it.description,
                    image = it.image,
                    link = it.link
                )
            )
        }

        return listOfGameNewStates
    }

    private fun updateGameNewsState(it: MutableList<GameNewsState>) {
        _uiState.value = it
        _requestState.value = States.SUCCESS
    }

    private fun updateRequestErrorState() {
        _requestState.value = States.ERROR
    }

    private fun formatDateToDateNews(str: String): String {
        return str.substring(startIndex = 0, endIndex = 16)
    }

    fun getAsyncImage(
        context: Context,
        imageUrl: String
    ): ImageRequest {
        return ImageRequest.Builder(context)
            .data(imageUrl)
            .size(Size.ORIGINAL)
            .crossfade(true)
            .build()
    }
}
