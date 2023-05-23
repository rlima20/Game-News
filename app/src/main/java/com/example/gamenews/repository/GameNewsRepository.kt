package com.example.gamenews.repository

import com.example.gamenews.model.GameNewsState
import kotlinx.coroutines.flow.Flow

interface GameNewsRepository {
    suspend fun getAllGameNews(): Flow<List<GameNewsState>>
}