# Scope or singleton

For ease of use, I added TODO filter. To follow links you need to create filters - click to `TODO`
in bottom panel -> icon
filter ![image](https://user-images.githubusercontent.com/121166010/214673108-b36497d7-85a4-4086-8beb-c6e8dbe297ad.png)
-> Edit Filters -> add pattern `\bdagger\b.*` and filter `dagger`

## We have two ways to implement a singleton objects (two for subcomponent and two for appComponent)
_but first we need create two another annotations_

```kotlin
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class OrderScope // for subcomponent - objects marked with this annotation will have the same lifecycle as subcomponent

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class AppScope  // for appComponent - objects marked with this annotation will have the same lifecycle as AppComponent

```

### first - with inject annotation
#### create singleton objects
```kotlin
@AppScope
class NetworkUtils @Inject constructor(){
    fun showMessage(message: String): String {
        return "NetworkHelper: $message"
    }
}
```

```kotlin
@OrderScope
class OrderRepository @Inject constructor(){
    //...
}
```

#### add getting method in appComponent
```kotlin
@AppScope
@Component
interface AppComponent {
    fun getOrderComponent(): OrderComponent

    fun getNetworkUtils(): NetworkUtils

}
```
#### add getting method in subcomponent
```kotlin
@OrderScope
@Subcomponent
interface OrderComponent {
    fun getOrderRepository(): OrderRepository
    
    fun getNetworkUtils(): NetworkUtils

}
```

#### Application
```kotlin
class App: Application() {
    val appComponent: AppComponent = DaggerAppComponent.create()

}
```

#### OrderActivity (second activity)
```kotlin
class OrderActivity: ComponentActivity() {

    lateinit var orderComponent: OrderComponent
    lateinit var orderRepository: OrderRepository
    lateinit var networkUtils: NetworkUtils

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        orderComponent = (application as App).appComponent.getOrderComponent()
        orderRepository = orderComponent.getOrderRepository()
        NetworkUtils = orderComponent.getNetworkUtils()

        //...
    }
}
```

### second - with inject annotation

#### create singleton objects
```kotlin
@AppScope
class NetworkUtils {
    fun showMessage(message: String): String {
        return "NetworkHelper: $message"
    }
}
```

```kotlin
@OrderScope
class OrderRepository {
    //...
}
```

#### create modules 
`AppComponent module`
```kotlin
@Module
class AppModule {
    @AppScope
    @Provides
    fun provideDatabaseHelper(): DatabaseHelper = DatabaseHelper()
}
```
`Subcomponent module`
```kotlin
@Module
class OrderModule {
    @OrderScope
    @Provides
    fun provideOrderRepository(): OrderRepository {
        return OrderRepository()
    }
}
```

#### create appComponent
```kotlin
@AppScope
@Component(modules = [AppModule::class])
interface AppComponent {
    // ...
    fun getOrderComponent(): OrderComponent

    fun getDatabaseHelper(): DatabaseHelper

}
```

#### create subcomponent
```kotlin
@OrderScope
@Subcomponent(modules = [OrderModule::class])
interface OrderComponent {
    fun getOrderRepository(): SubcomOrderRepository

    fun getDatabaseHelper(): DatabaseHelper

}
```

#### application
```kotlin
class App: Application() {
    val appComponent: AppComponent = DaggerAppComponent.create()

}
```

#### OrderActivity (second screen)
```kotlin
class OrderActivity: ComponentActivity() {

    lateinit var orderComponent: OrderComponent
    lateinit var orderRepository: OrderRepository
    lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        orderComponent = (application as App).appComponent.getOrderComponent()
        orderRepository = orderComponent.getOrderRepository()
        databaseHelper = orderComponent.getDatabaseHelper()
        //...
    }
}


```
