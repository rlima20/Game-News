package com.example.gamenews.repository

import com.example.gamenews.model.GameNewsDTO
import com.example.gamenews.model.GameNewsState
import com.example.gamenews.provider.local.listOfNews
import com.example.gamenews.provider.remote.GameNewsService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GameNewsRepositoryImpl(
    private val gameNewsService: GameNewsService
) : GameNewsRepository {
    override fun getAllGameNews(): Flow<List<GameNewsDTO>> = flow {
        with(gameNewsService.getAllGameNews()) {
            if (this.isSuccessful) this.body()?.let { emit(it) } else emit(emptyList())
        }
    }

    override fun getAllGameNewsLocal(): List<GameNewsState> {
        return listOfNews
    }
}
