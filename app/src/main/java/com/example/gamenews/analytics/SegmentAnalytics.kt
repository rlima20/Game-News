package com.example.gamenews.analytics

import com.segment.analytics.kotlin.core.Analytics

class SegmentAnalytics(private val segment: Analytics) {

    fun trackItemViewedSegment(
        itemName: String,
        origin: String?,
        screenName: String?,
        screenClass: String?,
    ) {
        segment.track(
            "Item Viewed",
            mapOf(
                "item_name" to itemName,
                "origin" to origin,
                "screen_name" to screenName,
                "screen_class" to screenClass,
            ),
        )
    }

    fun trackScreenViewedSegment(
        screenName: String,
        origin: String,
    ) {
        segment.track(
            "Screen Viewed",
            mapOf(
                "screen_name" to screenName,
                "screen_class" to origin,
            ),
        )
    }
}
