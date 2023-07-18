package com.example.gamenews.usecases

import com.example.gamenews.model.GameNewsDTO
import com.example.gamenews.provider.remote.Either
import kotlinx.coroutines.flow.Flow

interface GameNewsUseCase {
    fun invokeGameNews(): Flow<Either<List<GameNewsDTO>?, Throwable>>
    fun invokeGameNewsByQuery(
        query: String,
        quantifier: Int,
    ): Flow<Either<List<GameNewsDTO>?, Throwable>>

    fun invokeGameNewsByQueryLocal(
        query: String,
        quantifier: Int,
    ): Flow<Either<List<GameNewsDTO>?, Throwable>>
}
