package com.example.gamenews.repository

import com.example.gamenews.extensions.validateRequest
import com.example.gamenews.model.GameNewsDTO
import com.example.gamenews.model.GameNewsState
import com.example.gamenews.provider.local.listOfNews
import com.example.gamenews.provider.remote.GameNewsService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GameNewsRepositoryImpl(
    private val gameNewsService: GameNewsService,
) : GameNewsRepository {
    override fun getAllGameNews(): Flow<List<GameNewsDTO>?> = flow {
        validateRequest(gameNewsService.getAllGameNews())
    }

    override fun getAllGameNewsLocal(): List<GameNewsState> {
        return listOfNews
    }

    override fun geAllGameNewsByQuery(query: String, page: Int) = flow {
        validateRequest(gameNewsService.getAllGameNewsByQuery(query, page))
    }

    override fun geAllGameNewsByQueryLocal(query: String, page: Int): Flow<List<GameNewsDTO>?> =
        flow {
            emit(gameNewsService.getAllGameNewsByQueryLocal())
        }
}
