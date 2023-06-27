package com.example.gamenews.base

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import androidx.test.core.app.ApplicationProvider
import com.example.gamenews.R
import com.example.gamenews.di.appModules
import io.mockk.clearAllMocks
import io.mockk.mockk
import io.mockk.unmockkAll
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.android.controller.ActivityController
import org.robolectric.annotation.Config
import org.robolectric.annotation.LooperMode
import org.robolectric.annotation.LooperMode.Mode.PAUSED

@RunWith(RobolectricTestRunner::class)
@Config(
    sdk = [Build.VERSION_CODES.O_MR1],
    application = RobolectricTestApplication::class
)
@LooperMode(PAUSED)
abstract class RobolectricTestRunner {

    private lateinit var activityController: ActivityController<AppCompatActivity>

    internal lateinit var activity: AppCompatActivity

    open fun onSetup() {}

    open fun onTearDown() {}

    @Before
    fun setUp() {
        stopKoin()
        startKoin {
            androidContext(ApplicationProvider.getApplicationContext())
            modules(
                appModules
            )
        }

        activityController = Robolectric.buildActivity(AppCompatActivity::class.java)
        activity = activityController.get()
        activity.setTheme(R.style.Theme_GameNews)

        onSetup()
    }

    @After
    fun tearDown() {
        onTearDown()

        activity = mockk()
        activityController.close()

        stopKoin()
        unmockkAll()
        clearAllMocks()
    }
}
