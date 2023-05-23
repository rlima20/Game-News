package com.example.gamenews.repository

import com.example.gamenews.model.GameNewsState
import com.example.gamenews.model.GameNewsDTO
import com.example.gamenews.provider.remote.GameNewsService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GameNewsRepositoryImpl(
    private val gameNewsService: GameNewsService
) : GameNewsRepository {
    override suspend fun getAllGameNews(): Flow<List<GameNewsState>> = flow {
        emit(toMap(gameNewsService.getAllGameNews()))
    }
}

private fun toMap(listOfGameNewsDTO: List<GameNewsDTO>): List<GameNewsState> {
    val listOfGameNewStates: MutableList<GameNewsState> = mutableListOf()

    listOfGameNewsDTO.forEach {
        listOfGameNewStates.add(
            GameNewsState(
                title = it.title,
                date = it.date,
                description = it.description,
                image = it.image,
                link = it.link
            )
        )
    }

    return listOfGameNewStates
}
