package com.rustamsaga.dagger

import com.rustamsaga.dagger.di.DaggerAppComponent
import dagger.multibindings.StringKey
import javax.inject.Inject

fun main() {

    println("test @IntoSet and @ElementsIntoSet.")
    val appComponent = DaggerAppComponent.create()
    val testSets = AnalyticsTracker.Event.Open
    appComponent.testElementsIntoSet.getTestElements(testSets)

    println("\nTest @IntoMap.")
    appComponent.testIntoMap.getTestIntoMap(
        "telegram", AnalyticsTracker.Event.Open
    )
    appComponent.testIntoMap.getTestIntoMap(
        "logger", AnalyticsTracker.Event.Close
    )
}


class TestElementsIntoSet @Inject constructor(
    private val setAnalytics: Set<@JvmSuppressWildcards AnalyticsTracker>
) {
    fun getTestElements(event: AnalyticsTracker.Event) {
        setAnalytics.forEach { analytic ->
            analytic.trackEvent(event)
        }
    }
}

class TestIntoMap @Inject constructor(
    private val mapAnalytics: Map<String, @JvmSuppressWildcards AnalyticsTracker>
) {
    fun getTestIntoMap(key: String, event: AnalyticsTracker.Event) {
        mapAnalytics.forEach { mapAnalytic ->
            if (mapAnalytic.key == key)
                mapAnalytic.value.trackEvent(event)
        }
    }
}