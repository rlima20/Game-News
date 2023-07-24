package com.example.gamenews.di

import com.google.firebase.analytics.FirebaseAnalytics
import org.koin.dsl.module

val firebaseAnalyticsDI = module {
    single { FirebaseAnalytics.getInstance(get()) }
}
