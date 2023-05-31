package com.example.gamenews

const val BASE_URL = "https://videogames-news2.p.rapidapi.com"
const val API_KEY = "X-RapidAPI-Key: 2c2ef88b18mshc420370b1d1f51ep1ce3a0jsna97162cce8e2"
const val API_HOST = "X-RapidAPI-Host: videogames-news2.p.rapidapi.com"

enum class LoadingState(value: Boolean) {
    NOT_LOADING(false),
    FULL_LOADING(true),
    SIMPLE_LOADING(true)
}
