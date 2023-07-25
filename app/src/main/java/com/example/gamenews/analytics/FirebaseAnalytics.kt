package com.example.gamenews.analytics

import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics

class FirebaseAnalytics(private val firebaseAnalytics: FirebaseAnalytics) {

    fun trackItemViewed(
        itemName: String,
        origin: String,
        screenName: String,
        screenClass: String,
    ) {
        firebaseAnalytics.logEvent(
            FirebaseAnalytics.Event.VIEW_ITEM,
            Bundle().apply {
                putString(FirebaseAnalytics.Param.ITEM_NAME, itemName)
                putString(FirebaseAnalytics.Param.ORIGIN, origin)
                putString(FirebaseAnalytics.Param.SCREEN_NAME, screenName)
                putString(FirebaseAnalytics.Param.SCREEN_CLASS, screenClass)
            },
        )
    }

    fun trackScreenViewed(
        screenName: String,
        origin: String,
    ) {
        firebaseAnalytics.logEvent(
            FirebaseAnalytics.Event.SCREEN_VIEW,
            Bundle().apply {
                putString(FirebaseAnalytics.Param.SCREEN_NAME, screenName)
                putString(FirebaseAnalytics.Param.SCREEN_CLASS, origin)
            },
        )
    }

    fun trackGameItemViewed(gameName: String) {
        firebaseAnalytics.logEvent(
            FirebaseAnalytics.Event.VIEW_ITEM,
            Bundle().apply {
                putString(
                    FirebaseAnalytics.Param.ITEM_NAME,
                    gameName,
                )
            },
        )
    }

    fun trackImageClicked(gameName: String) {
        firebaseAnalytics.logEvent(
            FirebaseAnalytics.Event.SELECT_ITEM,
            Bundle().apply {
                putString(
                    FirebaseAnalytics.Param.ITEM_NAME,
                    gameName,
                )
            },
        )
    }

    fun trackGameItemFiltered(gameName: String) {
        firebaseAnalytics.logEvent(
            FirebaseAnalytics.Event.SELECT_ITEM,
            Bundle().apply {
                putString(
                    FirebaseAnalytics.Param.ITEM_NAME,
                    gameName,
                )
            },
        )
    }

    fun trackSearchBarClicked() {
        firebaseAnalytics.logEvent(
            FirebaseAnalytics.Event.SEARCH,
            Bundle().apply {
                putString(
                    FirebaseAnalytics.Param.SEARCH_TERM,
                    "search_bar",
                )
            },
        )
    }

    fun trackAdvancedSearchBarClicked() {
        firebaseAnalytics.logEvent(
            FirebaseAnalytics.Event.SEARCH,
            Bundle().apply {
                putString(
                    FirebaseAnalytics.Param.SEARCH_TERM,
                    "advanced_search_bar",
                )
            },
        )
    }

    fun trackQuantifierClicked(quantifier: String) {
        firebaseAnalytics.logEvent(
            FirebaseAnalytics.Event.SELECT_ITEM,
            Bundle().apply {
                putString(
                    FirebaseAnalytics.Param.ITEM_NAME,
                    quantifier,
                )
            },
        )
    }

    fun trackSubmitButtonClicked() {
        firebaseAnalytics.logEvent(
            FirebaseAnalytics.Event.SELECT_ITEM,
            Bundle().apply {
                putString(
                    FirebaseAnalytics.Param.ITEM_NAME,
                    "submit_button",
                )
            },
        )
    }

    fun trackCloseButtonClicked() {
        firebaseAnalytics.logEvent(
            FirebaseAnalytics.Event.SELECT_ITEM,
            Bundle().apply {
                putString(
                    FirebaseAnalytics.Param.ITEM_NAME,
                    "close_button",
                )
            },
        )
    }
}
