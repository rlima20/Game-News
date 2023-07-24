package com.example.gamenews.di

import com.example.gamenews.analytics.Analytics
import org.koin.dsl.module

val analyticsDI = module {
    single { Analytics(get()) }
}
