package com.example.gamenews.di // ktlint-disable filename

import com.example.gamenews.usecases.GameNewsUseCase
import com.example.gamenews.usecases.GameNewsUseCaseImpl
import org.koin.dsl.module

val gameNewsUseCaseModuleDI = module {
    factory<GameNewsUseCase> { GameNewsUseCaseImpl(get()) }
}
