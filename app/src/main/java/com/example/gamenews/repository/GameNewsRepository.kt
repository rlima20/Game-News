package com.example.gamenews.repository

import com.example.gamenews.model.GameNewsDTO
import com.example.gamenews.model.GameNewsState
import kotlinx.coroutines.flow.Flow

interface GameNewsRepository {
    fun getAllGameNews(): Flow<List<GameNewsDTO>?>
    fun getAllGameNewsLocal(): List<GameNewsState>
    fun geAllGameNewsByQuery(query: String, page: Int): Flow<List<GameNewsDTO>?>
    fun geAllGameNewsByQueryLocal(query: String = "Mario", page: Int = 3): Flow<List<GameNewsDTO>?>
}
