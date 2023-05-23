package com.example.gamenews.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.request.ImageRequest
import coil.size.Size
import com.example.gamenews.model.GameNewsState
import com.example.gamenews.repository.GameNewsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GameNewsViewModel(
    private val gameNewsRepository: GameNewsRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(mutableListOf<GameNewsState>())
    val uiState: StateFlow<List<GameNewsState>> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            getListOfNews()
        }
    }

    private suspend fun getListOfNews() {
        viewModelScope.launch {
            gameNewsRepository.getAllGameNews().collect { listOfGameState ->
                _uiState.value = listOfGameState.toMutableList()
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
}
