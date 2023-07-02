package com.example.gamenews.provider.remote

sealed class Either<out S, out F> {

    data class Failure<out F>(val failure: F) : Either<Nothing, F>()

    data class Success<out S>(val success: S) : Either<S, Nothing>()

    val isSuccess get() = this is Success<S>
    val isFailure get() = this is Failure<F>

    fun either(onSuccess: (S) -> Any, onFailure: (F) -> Any): Any = when (this) {
        is Failure -> onFailure(failure)
        is Success -> onSuccess(success)
    }
}
