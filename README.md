# Annotation @Inject

For ease of use, I added TODO filter. To follow links you need to create filters -
 click to `TODO` in bottom panel -> icon filter ![image](https://user-images.githubusercontent.com/121166010/214673108-b36497d7-85a4-4086-8beb-c6e8dbe297ad.png) -> Edit Filters -> add pattern `\bdagger\b.*` and filter `dagger` 


#### We used this annotation, but it can be used with the object constructor and _`its`_ methods.


## Dependency Class Creation
```
// Not added to @Module
class MainActivityPresenter @Inject constructor(
    private val localDatabase: LocalDatabase,
    @Named("dev") val serverDev: Server,
    @Named("client") val serverClient: Server
) {
    // ...
    override fun toString(): String {
        return "Presenter: $localDatabase, $serverDev, $serverClient"
    }

    // When the dagger2 creates this class (MainActivityPresenter),
    // it will call postInit immediately after creation.
    @Inject
    fun postInit(networkUtils: NetworkUtils){
        // ...
    }
}
```

```
// Not added to @Module
class NetworkUtils @Inject constructor() {
    override fun toString(): String = "NetworkUtils"
}
```

```
// Not added to @Module
class LocalDatabase @Inject constructor() {
    override fun toString(): String = "LocalDatabase"
}
```

```
class Server(private val route: String) {
    override fun toString(): String = route
}

```

## Module
```
@Module
class MainModule {

    @Provides
    @Named("dev")
    fun provideServerDev(): Server {
        return Server("dev.server.com")
    }

    @Provides
    @Named("client")
    fun provideServerClient(): Server {
        return Server("client.server.com")
    }

}
```

## Component
```
@Component(modules = [MainModule::class])
interface AppComponent {

    fun getMainActivityPresenter(): MainActivityPresenter

    fun getNetworkUtils(): NetworkUtils
}
```

## MainActivity
```

class MainActivity : ComponentActivity() {
    lateinit var presenter: MainActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            presenter = (application as App).appComponent.getMainActivityPresenter()
            // ...
    }

    // dagger-Annotation Inject/003
    // When the dagger2 creates this (MainActivityPresenter,
    // it will call postInit immediately after creation.
    @Inject
    fun postInit(networkUtils: NetworkUtils) {
        // ...
    }
}
```


