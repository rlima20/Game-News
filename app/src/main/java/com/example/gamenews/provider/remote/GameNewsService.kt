package com.example.gamenews.provider.remote

import com.example.gamenews.model.GameNewsDTO
import retrofit2.http.GET

interface GameNewsService {
    @GET("/videogames_news/recent")
    suspend fun getAllGameNews(): List<GameNewsDTO>
}
