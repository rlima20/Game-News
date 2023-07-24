package com.example.gamenews.usecases

import com.example.gamenews.model.GameNewsDTO
import com.example.gamenews.provider.remote.Either
import com.example.gamenews.repository.GameNewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GameNewsUseCaseImpl(
    private val gameNewsRepository: GameNewsRepository,
) : GameNewsUseCase {
    override fun invokeGetAllGameNews(): Flow<Either<List<GameNewsDTO>?, Throwable>> = flow {
        try {
            val flowListOfGameNews = gameNewsRepository.getAllGameNews()
            flowListOfGameNews.collect { listOfGameNewsDTO ->
                emit(
                    Either.Success(listOfGameNewsDTO),
                )
            }
        } catch (exception: Exception) {
            emit(Either.Failure(exception))
        }
    }

    override fun invokeGetAllGameNewsByQuery(
        query: String,
        quantifier: Int,
    ): Flow<Either<List<GameNewsDTO>?, Throwable>> = flow {
        try {
            val flowListOfGameNews = gameNewsRepository.geAllGameNewsByQuery(query, quantifier)
            flowListOfGameNews.collect { listOfGameNewsDTO ->
                emit(
                    Either.Success(listOfGameNewsDTO),
                )
            }
        } catch (exception: Exception) {
            emit(Either.Failure(exception))
        }
    }

    override fun invokeGetAllGameNewsByQueryLocal(
        query: String,
        quantifier: Int,
    ): Flow<Either<List<GameNewsDTO>?, Throwable>> = flow {
        try {
            val flowListOfGameNews = gameNewsRepository.geAllGameNewsByQueryLocal(query, quantifier)
            flowListOfGameNews.collect { listOfGameNewsDTO ->
                emit(
                    Either.Success(listOfGameNewsDTO),
                )
            }
        } catch (exception: Exception) {
            emit(Either.Failure(exception))
        }
    }
}
