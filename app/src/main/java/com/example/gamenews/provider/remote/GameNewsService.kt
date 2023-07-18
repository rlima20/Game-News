package com.example.gamenews.provider.remote

import com.example.gamenews.API_HOST
import com.example.gamenews.API_KEY
import com.example.gamenews.model.GameNewsDTO
import com.example.gamenews.provider.local.listOfNewsByQueryDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface GameNewsService {

    @Headers(API_KEY, API_HOST)
    @GET("/videogames_news/recent")
    suspend fun getAllGameNews(): Response<List<GameNewsDTO>>?

    @Headers(API_KEY, API_HOST)
    @GET("/videogames_news/search_news")
    suspend fun getAllGameNewsByQuery(
        @Query("query") query: String,
        @Query("per_page") perPage: Int,
    ): Response<List<GameNewsDTO>>?

    fun getAllGameNewsByQueryLocal(): List<GameNewsDTO>? {
        return listOfNewsByQueryDTO
    }
}
