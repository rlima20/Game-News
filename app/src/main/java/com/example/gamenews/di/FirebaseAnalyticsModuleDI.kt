package com.example.gamenews.di

import com.example.gamenews.Analytics
import com.google.firebase.analytics.FirebaseAnalytics
import org.koin.dsl.module

val firebaseAnalyticsDI = module {
    single { FirebaseAnalytics.getInstance(get()) }
}

val analyticsDI = module {
    single { Analytics(get()) }
}
