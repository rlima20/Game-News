package com.example.gamenews.di

import com.example.gamenews.provider.remote.GameNewsService
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val BASE_URL = "https://videogames-news2.p.rapidapi.com"

val network = module {
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
