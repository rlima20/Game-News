package com.example.gamenews.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.request.ImageRequest
import coil.size.Size
import com.example.gamenews.Analytics
import com.example.gamenews.extensions.removeSpaces
import com.example.gamenews.mappers.toMap
import com.example.gamenews.model.GameNewsState
import com.example.gamenews.model.States
import com.example.gamenews.provider.local.listOfNewsByQueryDTO
import com.example.gamenews.provider.local.mutableListOfNews
import com.example.gamenews.usecases.GameNewsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Locale

@Suppress("DEPRECATION")
class GameNewsViewModel(
    private val gameNewsUseCase: GameNewsUseCase,
    private val analytics: Analytics,
) : ViewModel() {

    /* Feature flags */
    private val _shouldSearchFromAPI = MutableStateFlow(false)
    val shouldSearchFromAPI: StateFlow<Boolean> = _shouldSearchFromAPI.asStateFlow()

    private val _shouldActivateAdvancedSearch = MutableStateFlow(true)
    val shouldActivateAdvancedSearch: StateFlow<Boolean> =
        _shouldActivateAdvancedSearch.asStateFlow()
    /* End of Feature flags */

    // The value of these variables should not be changed
    private val _uiState = MutableStateFlow<MutableList<GameNewsState>?>(null)
    val uiState: StateFlow<List<GameNewsState>?> = _uiState.asStateFlow()

    private val _uiStateFiltered = MutableStateFlow<MutableList<GameNewsState>?>(null)
    val uiStateFiltered: StateFlow<List<GameNewsState>?> = _uiStateFiltered.asStateFlow()

    private val _requestStatus = MutableStateFlow(States.LOADING)
    val requestStatus: StateFlow<States>
        get() = _requestStatus

    private val _imageDialog = MutableStateFlow(false)
    val imageDialog: StateFlow<Boolean> = _imageDialog.asStateFlow()

    private val _quantifier = MutableStateFlow(9)
    val quantifier: StateFlow<Int> = _quantifier.asStateFlow()

    private val _advancedSearchBarText = MutableStateFlow("")
    val advancedSearchBarText: StateFlow<String> = _advancedSearchBarText.asStateFlow()

    private val _isScreenEnabled = MutableStateFlow(false)
    val isScreenEnabled: StateFlow<Boolean> = _isScreenEnabled.asStateFlow()

    init {
        viewModelScope.launch {
            if (shouldSearchFromAPI.value) {
                fetchData()
            } else {
                fetchLocalData()
            }
        }
    }

    private fun updateGameNewsState(it: MutableList<GameNewsState>?) {
        _uiState.value = it
        _requestStatus.value = if (it != null) States.SUCCESS else States.ERROR
    }

    private fun updateRequestErrorState() {
        _requestStatus.value = States.ERROR
    }

    fun trackScreenViewed() {
        analytics.trackScreenView("HomeFragment")
    }

    fun trackAdvancedSearchViewed() {
        analytics.trackAdvancedSearchViewed()
    }

    fun fetchLocalData() {
        _requestStatus.value = States.LOADING
        updateGameNewsState(mutableListOfNews)
    }

    fun setScreenEnabled(enabled: Boolean) {
        _isScreenEnabled.value = enabled
    }

    fun updateAdvancedSearchStates(
        quantifier: Int,
        advancedSearchBarText: String,
    ) {
        _advancedSearchBarText.value = advancedSearchBarText
        _quantifier.value = quantifier
    }

    fun filterListOfGameNews(
        filterParameter: String,
    ) {
        clearFilteredListOfGameNews()
        val textWithoutSpaces = filterParameter.removeSpaces().toLowerCase(Locale.ROOT)
        val listFiltered: MutableList<GameNewsState> = mutableListOf()

        uiState.value?.forEach {
            if (it.title.toLowerCase(Locale.ROOT)
                    .contains(textWithoutSpaces.toLowerCase(Locale.ROOT)) ||
                it.description.toLowerCase(Locale.ROOT)
                    .contains(textWithoutSpaces.toLowerCase(Locale.ROOT))
            ) {
                listFiltered.add(it)
            }
        }
        _uiStateFiltered.value = listFiltered
    }

    fun fetchDataByQueryLocal(
        query: String = "",
        quantifier: Int = 1,
    ) {
        _requestStatus.value = States.LOADING
        updateGameNewsState(toMap(listOfNewsByQueryDTO))
    }

    fun clearFilteredListOfGameNews() {
        _uiStateFiltered.value?.clear()
    }

    fun setImageDialog(value: Boolean): Boolean {
        _imageDialog.value = value
        return imageDialog.value
    }

    fun fetchData() {
        _requestStatus.value = States.LOADING
        viewModelScope.launch {
            gameNewsUseCase.invokeGameNews().collect { result ->
                result.either(
                    onSuccess = { updateGameNewsState(toMap(it)) },
                    onFailure = { updateRequestErrorState() },
                )
            }
        }
    }

    fun fetchDataByQuery(
        query: String = "",
        quantifier: Int = 1,
    ) {
        _requestStatus.value = States.LOADING
        viewModelScope.launch {
            gameNewsUseCase.invokeGameNewsByQuery(query, quantifier).collect { result ->
                result.either(
                    onSuccess = { updateGameNewsState(toMap(it)) },
                    onFailure = { updateRequestErrorState() },
                )
            }
        }
    }

    fun getAsyncImage(
        context: Context,
        imageUrl: String,
    ): ImageRequest {
        return ImageRequest.Builder(context)
            .data(imageUrl)
            .size(Size.ORIGINAL)
            .crossfade(true)
            .build()
    }
}
