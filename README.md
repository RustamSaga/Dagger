# Builder and Factory

For ease of use, I added TODO filter. To follow links you need to create filters - click to `TODO`
in bottom panel -> icon
filter ![image](https://user-images.githubusercontent.com/121166010/214673108-b36497d7-85a4-4086-8beb-c6e8dbe297ad.png)
-> Edit Filters -> add pattern `\bdagger\b.*` and filter `dagger`

#### Customization of the Component using a builder or factory.

## Builder

1. To avoid calling the context in the module (AppModule) - this way better than passing context in the module constructor.
```kotlin
@Component(modules = [AppModule::class])
interface AppComponent {

    // ...

    @Component.Builder
    interface AppCompBuilder {
        fun buildAppComp(): AppComponent  // must have
        
        @BindsInstance  
        fun context(context: Context): AppCompBuilder
    }
}
```

2. init Component

```kotlin
class App : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
            .builder()
            .context(this)
            .buildAppComp() // getting AppComponent
    }
}

```
3. context takes from our custom builder
```kotlin
@Module
class AppModule {
 
    @Provides
    fun providePreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences("pref", MODE_PRIVATE)
    }

    @Provides
    fun provideResources(context: Context): Resources {
        return context.resources
    }
}
```

## Factory
Instead of Builder with multiple methods, we can use one method with multiple arguments.
1.
```kotlin
@Component(modules = [AppModule::class])
interface AppComponent {
    // ...

    @Component.Factory
    interface AppCompFactory {
        fun create(@BindsInstance context: Context, appModule: AppModule): AppComponent
    }
}
```
2. init Component
```kotlin
class App: Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        
        appComponent = DaggerAppComponent
            .factory()
            .create(this, AppModule())
    }
}
```
