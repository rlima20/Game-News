package com.example.gamenews.usecases

import com.example.gamenews.model.GameNewsDTO
import kotlinx.coroutines.flow.Flow

interface GameNewsUseCase {
    operator fun invoke(): Flow<Either<List<GameNewsDTO>?, Throwable>>
}
