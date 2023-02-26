# Components. Dependencies

For ease of use, I added TODO filter. To follow links you need to create filters - click to `TODO`
in bottom panel -> icon
filter ![image](https://user-images.githubusercontent.com/121166010/214673108-b36497d7-85a4-4086-8beb-c6e8dbe297ad.png)
-> Edit Filters -> add pattern `\bdagger\b.*` and filter `dagger`

### Dagger gives us another way of interacting between components.
In this case, we create two common components and indicate that one is dependent on the other.

### We create two normal components, each of them has different modules, but one delivers dependencies to another.

#### appComponent for deliver dependencies
```kotlin
@Component(modules = [DependenciesModule::class])
interface AppComponent {
    // must have for create another object in another components
    fun getDatabaseHelper(): DatabaseHelper
    fun getNetworkUtils(): NetworkUtils
}
```
##### appComponent's module
```kotlin
@Module
class DependenciesModule {

    @Provides
    fun provideDatabaseHelper(): DatabaseHelper = DatabaseHelper()

    @Provides
    fun provideNetworkUtils(): NetworkUtils = NetworkUtils()
}
```


#### mainComponent that needs dependencies for create presenter
```kotlin
@Component(modules = [MainModule::class], dependencies = [AppComponent::class])
interface BuilderComponent {
    fun getMainActivityPresenter(): MainActivityPresenter

}
```

##### mainComponent's module
```kotlin
@Module
class MainModule {
    @Provides
    fun provideMainActivityPresenter(
        databaseHelper: DatabaseHelper,
        networkUtils: NetworkUtils
    ): MainActivityPresenter {
        return MainActivityPresenter(databaseHelper, networkUtils)
    }
}
```

#### Init builderComponent
```kotlin
class App: Application() {
    val appComponent: AppComponent = DaggerAppComponent.create()
    
    val builderComponent: BuilderComponent =
        DaggerBuilderComponent
            .builder()
            .appComponent(appComponent)
            .build()
}
```

#### Activity
```kotlin
//...
    lateinit var builderComponent: BuilderComponent
    lateinit var presenter: MainActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        builderComponent = (application as App).builderComponent
        presenter = builderComponent.getMainActivityPresenter()

        //...
    }

```

