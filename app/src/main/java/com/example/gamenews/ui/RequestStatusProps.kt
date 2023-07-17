package com.example.gamenews.ui

import android.content.Context
import com.example.gamenews.model.GameNewsState
import com.example.gamenews.model.States
import com.example.gamenews.viewmodel.GameNewsViewModel

data class RequestStatusProps(
    val requestStatus: States,
    val quantifierState: Int,
    val advancedSearchBarText: String,
    var advancedSearchIconClickedValue: Boolean,
    val searchedText: String,
    val localContext: Context,
    val listOfGameNewsUiState: List<GameNewsState>?,
    val gameNewsViewModel: GameNewsViewModel,
    val shouldUseApi: Boolean,
    val isScreenEnabled: Boolean = false,
    val shouldActivateAdvancedSearch: Boolean = false,
    val onSearchTextChanged: (searchText: String) -> Unit,
    val onAdvancedSearchIconClicked: () -> Unit,
    val onSaveAdvancedSearchStates: (quantifierState: Int, advancedSearchBarText: String) -> Unit,
)
