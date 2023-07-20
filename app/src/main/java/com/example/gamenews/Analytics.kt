package com.example.gamenews

import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics

class Analytics(private val firebaseAnalytics: FirebaseAnalytics) {

    fun trackScreenView(screenName: String) {
        firebaseAnalytics.logEvent(
            FirebaseAnalytics.Event.SCREEN_VIEW,
            Bundle().apply {
                putString(
                    FirebaseAnalytics.Param.SCREEN_CLASS,
                    screenName,
                )
            },
        )
    }

    // todo: add more events
    fun trackAdvancedSearchViewed() {
        firebaseAnalytics.logEvent(
            FirebaseAnalytics.Event.VIEW_ITEM,
            Bundle().apply {
                putString(
                    FirebaseAnalytics.Param.ITEM_NAME,
                    "advanced_search",
                )
            },
        )
    }
}
