package com.example.gamenews.di

import com.example.gamenews.di.appModules
import com.example.gamenews.di.gameNewsUseCaseModuleDI
import com.example.gamenews.di.gameNewsViewModelDI
import com.example.gamenews.di.networkDI
import com.example.gamenews.di.repositoryDI
import io.kotlintest.shouldNotBe
import org.junit.Test

class DependencyInjectionTests {

    @Test
    fun `gameNewsViewModelDI test`() {
        gameNewsViewModelDI shouldNotBe null
    }

    @Test
    fun `gameNewsUseCaseModuleDI test`() {
        gameNewsUseCaseModuleDI shouldNotBe null
    }

    @Test
    fun `repositoryDI test`() {
        repositoryDI shouldNotBe null
    }

    @Test
    fun `netWorkDI test`() {
        networkDI shouldNotBe null
    }

    @Test
    fun `AppModulesDI test`() {
        appModules shouldNotBe null
    }
}
