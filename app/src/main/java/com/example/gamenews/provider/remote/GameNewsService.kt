package com.example.gamenews.provider.remote

import com.example.gamenews.API_HOST
import com.example.gamenews.API_KEY
import com.example.gamenews.model.GameNewsDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface GameNewsService {

    @Headers(API_KEY, API_HOST)
    @GET("/videogames_news/recent2")
    suspend fun getAllGameNews(): Response<List<GameNewsDTO>>?
}
