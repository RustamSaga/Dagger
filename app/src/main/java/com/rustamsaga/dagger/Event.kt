package com.rustamsaga.dagger

import javax.inject.Inject


// dagger-IntoSet-ElementsIntoSet-IntoMap/001 - create interface which will be used in generic SET (Set<AnalyticsTracer>)
interface AnalyticsTracker {
    fun trackEvent(event: Event){
        println("$this: ${event.name}")
    }

    sealed class Event(val name: String) {
        object Open: Event("open")
        object Close: Event("close")
    }
}

// dagger-IntoSet-ElementsIntoSet-IntoMap/002 - create another classes that implement AnalyticsTracker
class TelegramAnalytic @Inject constructor(): AnalyticsTracker {

    override fun toString() = "Analytic"

}

class Logger @Inject constructor(): AnalyticsTracker {

    override fun toString() = "Logger"
}

class DatabaseAnalytic @Inject constructor(): AnalyticsTracker {

    override fun toString() = "DatabaseAnalytic"
}

class FacebookAnalytic @Inject constructor(): AnalyticsTracker {

    override fun toString() = "FacebookAnalytic"
}

class FirebaseAnalytic @Inject constructor(): AnalyticsTracker {

    override fun toString() = "FirebaseAnalytic"
}


