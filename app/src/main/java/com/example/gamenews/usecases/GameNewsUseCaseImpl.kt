package com.example.gamenews.usecases

import com.example.gamenews.core.Either
import com.example.gamenews.model.GameNewsDTO
import com.example.gamenews.repository.GameNewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GameNewsUseCaseImpl(
    private val gameNewsRepository: GameNewsRepository
) : GameNewsUseCase {
    override fun invoke(): Flow<Either<List<GameNewsDTO>?, Throwable>> = flow {
        try {
            val flowListOfGameNews = gameNewsRepository.getAllGameNews()
            flowListOfGameNews.collect { listOfGameNewsDTO ->
                emit(
                    Either.Success(listOfGameNewsDTO)
                )
            }
        } catch (exception: Exception) {
            emit(Either.Failure(exception))
        }
    }
}
