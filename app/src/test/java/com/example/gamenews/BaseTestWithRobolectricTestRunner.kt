package com.example.gamenews

import android.app.Activity
import android.os.Build
import android.os.Handler
import android.os.Looper
import io.mockk.unmockkAll
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.android.controller.ActivityController
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
abstract class BaseTestWithRobolectricTestRunner {

    private lateinit var activityController: ActivityController<Activity>
    lateinit var activity: Activity

    open fun onSetup() {}
    open fun onTearDown() {}

    @Before
    fun setUp() {

        activityController = Robolectric.buildActivity(Activity::class.java)
            .create()
            .start()
            .resume()
            .visible()
        activity = activityController.get()

        onSetup()
    }

    @After
    fun tearDown() {
        onTearDown()
        unmockkAll()
    }

    fun waitingFor(timeInMillis: Long = 500, execute: () -> Unit) {
        Handler(Looper.getMainLooper()).postDelayed(execute, timeInMillis)
    }
}
