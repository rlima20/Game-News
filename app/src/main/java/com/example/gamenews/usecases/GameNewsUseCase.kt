package com.example.gamenews.usecases

import com.example.gamenews.model.GameNewsDTO
import com.example.gamenews.provider.remote.Either
import kotlinx.coroutines.flow.Flow

interface GameNewsUseCase {
    fun invokeGetAllGameNews(): Flow<Either<List<GameNewsDTO>?, Throwable>>
    fun invokeGetAllGameNewsByQuery(
        query: String,
        quantifier: Int,
    ): Flow<Either<List<GameNewsDTO>?, Throwable>>

    fun invokeGetAllGameNewsByQueryLocal(
        query: String,
        quantifier: Int,
    ): Flow<Either<List<GameNewsDTO>?, Throwable>>
}
