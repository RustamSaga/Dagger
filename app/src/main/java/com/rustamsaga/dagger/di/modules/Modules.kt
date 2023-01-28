package com.rustamsaga.dagger.di.modules

import com.rustamsaga.dagger.*
import dagger.Module
import dagger.Provides
import dagger.multibindings.ElementsIntoSet
import dagger.multibindings.IntoMap
import dagger.multibindings.IntoSet
import dagger.multibindings.StringKey

// dagger-IntoSet-ElementsIntoSet/003
//  - add @IntoSet or/and @ElementsIntoSet for giving AnalyticsTracker implementations
@Module
class SetModule {

    @IntoSet
    @Provides
    fun provideAnalytics(): AnalyticsTracker{
        return TelegramAnalytic()
    }

    @IntoSet
    @Provides
    fun provideLoggers(): AnalyticsTracker{
        return Logger()
    }
}


@Module
class ElementsIntoSetModule{

    @Provides
    @ElementsIntoSet
    fun provideDatabaseEventHelpers(
        databaseAnalytic: DatabaseAnalytic,
        facebookAnalytic: FacebookAnalytic,
        firebaseAnalytic: FirebaseAnalytic
    ): Set<AnalyticsTracker>{
        return setOf(databaseAnalytic, facebookAnalytic, firebaseAnalytic)
    }
}

// dagger-IntoMap/003 - add @IntoMap in provide functions for giving AnalyticsTracker implementations
@Module
class IntoMapModule{

    @IntoMap
    @StringKey("telegram")
    @Provides
    fun provideTelegramAnalytics(): AnalyticsTracker {
        return TelegramAnalytic()
    }

    @IntoMap
    @StringKey("logger")
    @Provides
    fun provideLogger(): AnalyticsTracker = Logger()
}