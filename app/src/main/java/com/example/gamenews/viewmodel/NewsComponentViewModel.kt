package com.example.gamenews.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import coil.request.ImageRequest
import coil.size.Size
import com.example.gamenews.listOfNews
import com.example.gamenews.model.News

class NewsComponentViewModel : ViewModel() {
    fun getListOfNews(): List<News> = listOfNews

    fun getAsyncImage(
        context: Context,
        imageUrl: String
    ): ImageRequest {
        return ImageRequest.Builder(context)
            .data(imageUrl)
            .size(Size.ORIGINAL)
            .crossfade(true)
            .build()
    }
}
