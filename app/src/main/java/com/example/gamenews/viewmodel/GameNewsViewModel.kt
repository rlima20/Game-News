package com.example.gamenews.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.request.ImageRequest
import coil.size.Size
import com.example.gamenews.LoadingState
import com.example.gamenews.model.GameNewsDTO
import com.example.gamenews.model.GameNewsState
import com.example.gamenews.repository.GameNewsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@Suppress("DEPRECATION")
class GameNewsViewModel(
    private val gameNewsRepository: GameNewsRepository
) : ViewModel() {

    private val _loading = MutableStateFlow(LoadingState.FULL_LOADING)
    val loading: StateFlow<LoadingState>
        get() = _loading

    private val _uiState = MutableStateFlow(mutableListOf<GameNewsState>())
    val uiState: StateFlow<List<GameNewsState>> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            getListOfNews(LoadingState.FULL_LOADING)
        }
    }

    private fun getListOfNews(state: LoadingState) {
        viewModelScope.launch {
            gameNewsRepository.getAllGameNews().collect { listOfGameState ->
                _uiState.value = toMap(listOfGameState).toMutableList()
                _loading.value = LoadingState.NOT_LOADING
            }
        }
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

    private fun toMap(listOfGameNewsDTO: List<GameNewsDTO>): List<GameNewsState> {
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
}

fun formatDateToDateNews(str: String): String {
    return str.substring(startIndex = 0, endIndex = 16)
}
