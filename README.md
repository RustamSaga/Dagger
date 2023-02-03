# Passing object to the component

For ease of use, I added TODO filter. To follow links you need to create filters -
 click to `TODO` in bottom panel -> icon filter ![image](https://user-images.githubusercontent.com/121166010/214673108-b36497d7-85a4-4086-8beb-c6e8dbe297ad.png) -> Edit Filters -> add pattern `\bdagger\b.*` and filter `dagger` 

## To use some object to which dagger doesn't have an access we need:
1. create module and put in constructor some object
```
@Module
class AppModule(private val context: Context) {
    //...
}
```

2. put the object in the graph (with @Provides)
```
@Module
class AppModule(private val context: Context) {

    @Provides
    fun provideContext(): Context {
        return context
    }
    
    //...
}
```

3. add module in component
```
@Component(modules = [AppModule::class, ...])
interface AppComponent {
// ...
}
```

4. create AppComponent by use Builder directly to put in the Module some object, to which dagger2 doesn't have an access 
```
class App: Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()
    }
}
```
#### And then dagger can use this object (context) to create other objects, even if they are created in other modules:
```
@Module
class MainModule {
    @Provides
    fun provideMainActivityPresenter(context: Context): MainActivityPresenter {
        return MainActivityPresenter(context = context)
    }
}
```

