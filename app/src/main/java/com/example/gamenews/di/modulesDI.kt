package com.example.gamenews

import com.example.gamenews.viewmodel.NewsComponentViewModel
import org.koin.dsl.module

val newsViewModelDI = module {
    factory { NewsComponentViewModel() }
}
