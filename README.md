# AssistedInject

For ease of use, I added TODO filter. To follow links you need to create filters - click to `TODO`
in bottom panel -> icon
filter ![image](https://user-images.githubusercontent.com/121166010/214673108-b36497d7-85a4-4086-8beb-c6e8dbe297ad.png)
-> Edit Filters -> add pattern `\bdagger\b.*` and filter `dagger`
 
## AssistedInject - passes objects to the dagger that it cannot create itself.

### Create some objects
```kotlin
class NetworkUtils @Inject constructor() {
    override fun toString(): String {
        return "NetworkUtils"
    }
}
```

### Create object with @AssistedInject - this object create when we need
```kotlin
data class ServiceApi @AssistedInject constructor(
    val networkUtils: NetworkUtils,
    @Assisted val id: Int,
    @Assisted("host") val host: String,  // Assisted have param, because we have two value String
    @Assisted("port") val port: String
) {}
```

### Create a class where serviceApi is required, but we use serviceApiFactory
```kotlin
data class MainActivityPresenter @Inject constructor(
    private val serviceApiFactory: ServiceApiFactory){

    @AssistedFactory
    interface ServiceApiFactory {
        fun create(
            id: Int,
            @Assisted("host") host: String,
            @Assisted("port") port: String = "80"
        ): ServiceApi
    }

    // Here we create serviceApi
    fun onCreate(id: Int, host: String, port: String = "80"): ServiceApi {
        return serviceApiFactory.create(id, host, port)
    }

    // Or like this
    val serviceApi = serviceApiFactory.create(1, "test.drive.inside")

}
```

### Create component and getter method
```kotlin
@Component
interface AppComponent {
    fun getMainActivityPresenter(): MainActivityPresenter
}
```

### Mini test
```kotlin
fun main(){
    val component: AppComponent = DaggerAppComponent.create()

    val presenter = component.getMainActivityPresenter()
    val serviceApi = presenter.onCreate(1, "test.drive.outside")
    val serviceApi2 = presenter.serviceApi

    println("presenter = $presenter")
    println("serviceApi = $serviceApi")
    println("serviceApi2 = $serviceApi2")

}
```