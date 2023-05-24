package com.example.gamenews.di

import com.example.gamenews.viewmodel.GameNewsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val gameNewsViewModelDI = module {
    viewModel { GameNewsViewModel(get()) }
}
