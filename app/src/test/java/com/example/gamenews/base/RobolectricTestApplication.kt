package com.example.gamenews.base

import android.app.Application
import com.example.gamenews.R

class RobolectricTestApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        setTheme(R.style.Theme_MaterialComponents)
    }
}
