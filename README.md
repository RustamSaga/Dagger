# Dagger mini-tutorial

For ease of use, I added TODO filter. To follow links you need to create filters -
 click to `TODO` in bottom panel -> icon filter ![image](https://user-images.githubusercontent.com/121166010/214673108-b36497d7-85a4-4086-8beb-c6e8dbe297ad.png) -> Edit Filters -> add pattern `\bdagger\b.*` and filter `dagger` 


#### If we need to get multiple similar objects we use annotations: [@IntoSet](https://github.com/RustamSaga/Dagger/blob/dagger/003-additional_features/set_and_map/README.md#intoset), [@ElementsIntoSet](https://github.com/RustamSaga/Dagger/blob/dagger/003-additional_features/set_and_map/README.md#elementsintoset), [@IntoMap](https://github.com/RustamSaga/Dagger/blob/dagger/003-additional_features/set_and_map/README.md#intomap)

## `@IntoSet` 

#### Objects for provide
```
interface AnalyticsTracker {
    fun trackEvent(event: Event){
        println("$this: ${event.name}")
    }

    sealed class Event(val name: String) {
        object Open: Event("open")
        object Close: Event("close")
    }
}


class TelegramAnalytic @Inject constructor(): AnalyticsTracker {

    override fun toString() = "Analytic"

}

class Logger @Inject constructor(): AnalyticsTracker {

    override fun toString() = "Logger"
}

// ...
```

#### Module
```
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
```

#### Component
```
   fun injectActivity(activity: Activity)
```

#### Activity
```
    @Inject
    lateinit var eventSet: Set<@JvmSuppressWildcards AnalyticsTracker>
    
     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            (application as App).appComponent.injectActivity(this)
        //... 
        }
```

## `@ElementsIntoSet`

// all the same as intoSet except @Module
#### Module
```
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
```

## `IntoMap`

#### `Module`
```
    @IntoMap
    @EventKey(EventType.TELEGRAM) // custom annotation or @StringKey("telegram")
    @Provides
    fun provideTelegramAnalytics(): AnalyticsTracker {
        return TelegramAnalytic()
    }

    @IntoMap
    @EventKey(EventType.LOGGER) // custom annotation or @StringKey("logger")
    @Provides
    fun provideLogger(): AnalyticsTracker = Logger()
```

#### `Acivity`
```
    @Inject
    lateinit var mapAnalyticsTracker: Map<EventType, @JvmSuppressWildcards AnalyticsTracker>
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {    
            (application as App).appComponent.injectActivity(this)
            //...
        }
        //...
```

