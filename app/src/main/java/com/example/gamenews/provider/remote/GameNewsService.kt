package com.example.gamenews.provider.remote

import com.example.gamenews.model.GameNewsDTO
import retrofit2.http.GET
import retrofit2.http.Headers

interface GameNewsService {

    @Headers(
        "X-RapidAPI-Key: 2c2ef88b18mshc420370b1d1f51ep1ce3a0jsna97162cce8e2",
        "X-RapidAPI-Host: videogames-news2.p.rapidapi.com"
    )
    @GET("/videogames_news/recent")
    suspend fun getAllGameNews(): List<GameNewsDTO>
}
