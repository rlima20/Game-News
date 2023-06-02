package com.example.gamenews.usecases

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

sealed class Either<out S, out F> {

    data class Failure<out F>(val failure: F) : Either<Nothing, F>()

    data class Success<out S>(val success: S) : Either<S, Nothing>()

    val isSuccess get() = this is Success<S>
    val isFailure get() = this is Failure<F>

    fun <F> failure(a: F) = Failure(a)
    fun <S> success(b: S) = Success(b)

    fun either(onSuccess: (S) -> Any, onFailure: (F) -> Any): Any = when (this) {
        is Failure -> onFailure(failure)
        is Success -> onSuccess(success)
    }
}

typealias Result<T> = Either<T, Throwable>
typealias Failure<T> = Either.Failure<T>
typealias Success<T> = Either.Success<T>
