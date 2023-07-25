package com.example.gamenews.di

import com.example.gamenews.BASE_URL
import com.example.gamenews.analytics.FirebaseAnalytics
import com.example.gamenews.provider.remote.GameNewsService
import com.example.gamenews.repository.GameNewsRepository
import com.example.gamenews.repository.GameNewsRepositoryImpl
import com.example.gamenews.usecases.GameNewsUseCase
import com.example.gamenews.usecases.GameNewsUseCaseImpl
import com.example.gamenews.viewmodel.GameNewsViewModel
import com.segment.analytics.kotlin.android.Analytics
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val gameNewsUseCaseModuleDI = module {
    factory<GameNewsUseCase> { GameNewsUseCaseImpl(get()) }
}

val networkDI = module {
    single<Retrofit> {
        Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<GameNewsService> {
        val retrofit: Retrofit = get()
        retrofit.create(GameNewsService::class.java)
    }
}

val repositoryDI = module {
    factory<GameNewsRepository> { GameNewsRepositoryImpl(get()) }
}

val gameNewsViewModelDI = module {
    viewModel { GameNewsViewModel(get(), get(), get()) }
}

val firebaseAnalyticsDI = module {
    single { com.google.firebase.analytics.FirebaseAnalytics.getInstance(get()) }
}

val analyticsDI = module {
    single { FirebaseAnalytics(get()) }
}

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

val appModules = listOf(
    gameNewsViewModelDI,
    networkDI,
    repositoryDI,
    gameNewsUseCaseModuleDI,
    firebaseAnalyticsDI,
    analyticsDI,
    segmentDI,
)
