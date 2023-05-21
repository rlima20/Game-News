package com.example.gamenews

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

class AppApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        var listOfModules = module {
            single { newsViewModelDI }
        }

        org.koin.core.context.startKoin {
            androidContext(this@AppApplication)
            modules(listOfModules)
        }

/*        startKoin {
            androidContext(this@AppApplication)
            modules(listOfModules)
        }*/
    }
}
