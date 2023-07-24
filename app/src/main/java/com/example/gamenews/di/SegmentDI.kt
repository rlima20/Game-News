package com.example.gamenews.di

import com.segment.analytics.kotlin.android.Analytics
import org.koin.dsl.module

val segmentDI = module {
    single {
        Analytics("IgozKCkLOG1Cb37yd2oEDykVbsdIlqSq", get()) {
            // Automatically track Lifecycle events
            trackApplicationLifecycleEvents = true
            flushAt = 3
            flushInterval = 10
        }
    }
}
