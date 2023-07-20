package com.example.gamenews.di // ktlint-disable filename

import com.example.gamenews.viewmodel.GameNewsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val gameNewsViewModelDI = module {
    viewModel { GameNewsViewModel(get(), get()) }
}
