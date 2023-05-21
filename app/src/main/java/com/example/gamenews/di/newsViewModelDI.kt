package com.example.gamenews.di

import com.example.gamenews.viewmodel.NewsComponentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val newsViewModelDI = module {
    viewModel { NewsComponentViewModel() }
}
