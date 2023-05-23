package com.example.gamenews.di

import com.example.gamenews.repository.GameNewsRepository
import com.example.gamenews.repository.GameNewsRepositoryImpl
import org.koin.dsl.module

val repositoryDI = module {
    factory<GameNewsRepository> { GameNewsRepositoryImpl(get()) }
}
