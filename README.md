# Subcomponent. Transfer of objects
#### Second advantage - more flexible transfer of objects

For ease of use, I added TODO filter. To follow links you need to create filters - click to `TODO`
in bottom panel -> icon
filter ![image](https://user-images.githubusercontent.com/121166010/214673108-b36497d7-85a4-4086-8beb-c6e8dbe297ad.png)
-> Edit Filters -> add pattern `\bdagger\b.*` and filter `dagger`

## Builder (with getter method)

#### Objects
```kotlin
class MainActivityPresenter(
    val databaseHelper: DatabaseHelper,
    val networkUtils: NetworkUtils,
    val activity: Activity
) {}

class DatabaseHelper @Inject constructor() {
    fun showMessage(message: String): String {
        return "DatabaseHelper: $message"
    }
}

class NetworkUtils @Inject constructor() {
    fun showMessage(message: String): String {
        return "NetworkHelper: $message"
    }
}
```

#### Module
```kotlin
@Module
class MainModule {
    @Provides
    fun provideMainActivityPresenter(
        databaseHelper: DatabaseHelper,
        networkUtils: NetworkUtils,
        activity: Activity
    ): MainActivityPresenter {
        return MainActivityPresenter(databaseHelper, networkUtils, activity)
    }
}
```

#### Subcomponent
```kotlin
@Subcomponent(modules = [MainModule::class])
interface BuilderComponent {

    @Subcomponent.Builder
    interface Builder {
        @BindsInstance fun activity(activity: Activity): Builder
        fun build(): BuilderComponent
    }

    fun getMainActivityPresenter(): MainActivityPresenter

}
```

#### AppComponent (main component)
```kotlin
@Component
interface AppComponent {
    // ...
    fun getBuilderComponentBuilder(): BuilderComponent.Builder
}
```

#### Activity
```kotlin
class MainActivity : ComponentActivity() {

    lateinit var appComponent: AppComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        appComponent = (application as App).appComponent
        val builderComponent = appComponent.getBuilderComponentBuilder()
            .activity(this)
            .build()
        //...
    }
}
```

## Factory (with getter method)

#### Objects
```kotlin
class MainActivityPresenter(
    val databaseHelper: DatabaseHelper,
    val networkUtils: NetworkUtils,
    val activity: Activity
) {}

class DatabaseHelper @Inject constructor() {
    fun showMessage(message: String): String {
        return "DatabaseHelper: $message"
    }
}

class NetworkUtils @Inject constructor() {
    fun showMessage(message: String): String {
        return "NetworkHelper: $message"
    }
}
```
#### Module
```kotlin
@Module
class MainModule {
    @Provides
    fun provideMainActivityPresenter(
        databaseHelper: DatabaseHelper,
        networkUtils: NetworkUtils,
        activity: Activity
    ): MainActivityPresenter {
        return MainActivityPresenter(databaseHelper, networkUtils, activity)
    }
}
```

#### Subcomponent
```kotlin
@Subcomponent(modules = [MainModule::class])
interface FactoryComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance activity: Activity): FactoryComponent
    }

    fun getMainActivityPresenter(): MainActivityPresenter
}
```

#### AppComponent (main component)
```kotlin
@Component
interface AppComponent {
    // ...
    fun getFactoryComponentFactory(): FactoryComponent.Factory
}
```

#### Activity
```kotlin

class MainActivity : ComponentActivity() {

    lateinit var appComponent: AppComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        appComponent = (application as App).appComponent
        val factoryComponent = appComponent.getFactoryComponentFactory().create(this)
        
        //...
    }
}
```

## Inject Method
from dagger documentation:
Using @Module.subcomponents is better since it allows Dagger to detect if the subcomponent is ever requested.
Installing a subcomponent via a method on the parent component is an explicit request for that component, 
even if that method is never called. Dagger can’t detect that, and thus must generate the subcomponent 
even if you never use it.

#### Object
```kotlin
class InjectActivityPresenter(
    val databaseHelper: DatabaseHelper,
    val networkUtils: NetworkUtils,
    val activity: Activity
)

//...
```

#### Module
```kotlin
@Module(subcomponents = [InjectComponent::class]) // key moment
class InjectModule {

    @Provides
    fun provideInjectActivityPresenter(
        databaseHelper: DatabaseHelper,
        networkUtils: NetworkUtils,
        activity: Activity
    ): InjectActivityPresenter {
        return InjectActivityPresenter(databaseHelper, networkUtils, activity)
    }
}
```

#### Subcomponent
```kotlin
@Subcomponent
interface InjectComponent {

    // you can use builder or factory
    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance activity: Activity): InjectComponent
    }

    fun injectInjectActivity(injectActivity: InjectActivity)
}
```

#### AppComponent (main component)
```kotlin
@Component(modules = [InjectModule::class]) // key moment
interface AppComponent {
    // ...
    fun injectInjectActivity(injectActivity: InjectActivity)
}
```

#### Activity
```kotlin

class InjectActivity : ComponentActivity() {

    @Inject
    lateinit var injectComponentFactory: InjectComponent.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (application as App).appComponent.injectInjectActivity(this)
        val injectComponent = injectComponentFactory.create(this)
        
        //...
    }
}
```
