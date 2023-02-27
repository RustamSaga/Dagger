# MultiScope

For ease of use, I added TODO filter. To follow links you need to create filters - click to `TODO`
in bottom panel -> icon
filter ![image](https://user-images.githubusercontent.com/121166010/214673108-b36497d7-85a4-4086-8beb-c6e8dbe297ad.png)
-> Edit Filters -> add pattern `\bdagger\b.*` and filter `dagger`
 
## We have 3 cases
1. Global singleton object - can be used by any subcomponent
2. Local singleton object - can be used just one subcomponent
3. Common singleton object - can only use defined subcomponents

## Global singleton object
1. create annotation
```kotlin
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class AppScope
```
2. create your singleton object
```kotlin
@AppScope // this class is a shared object for all subcomponents
class AppDatabase() {
    //...
}
```
3. create module and add provide method 
```kotlin
@Module
class AppModule() {
    @AppScope
    @Provides
    fun provideAppDatabase(): AppDatabase = AppDatabase()
}
```
4. create appComponent and subcomponents
```kotlin
@AppScope
@Component(modules = [AppModule::class])
interface AppComponent {
    // ...
    fun getOrderComponent(): OrderComponent
    fun getUserComponent(): UserComponent

}
```

```kotlin
@Subcomponent
interface UserComponent {
    fun getAppDatabase(): AppDatabase
    
}
```

```kotlin
@Subcomponent
interface OrderComponent {
    fun getAppDatabase(): AppDatabase
}
```

5. init in application
```kotlin
class App: Application() {
    val appComponent: AppComponent = DaggerAppComponent.create()
}
```

6. init in activities
```kotlin

class OrderActivity : ComponentActivity() {
    lateinit var orderComponent: OrderComponent
    lateinit var appDatabase: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        orderComponent = (application as App).appComponent
            .getOrderComponent()
        appDatabase = orderComponent.getAppDatabase()
        
        //...
    }
}
```
```kotlin
class UserActivity : ComponentActivity() {
    lateinit var userComponent: UserComponent
    lateinit var appDatabase: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        userComponent = (application as App).appComponent
            .getUserComponent()
        appDatabase = orderComponent.getAppDatabase()
        
        //...
    }
}
```

## Local singleton object by module
1. create annotation for subcomponent
```kotlin
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class OrderScope
```

```kotlin
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class UserScope
```

2. create singleton objects
```kotlin
class UiHelper(activity: Activity){
    //...
}
```

3. create module
```kotlin
@Module
class OrderModule {
    @OrderScope
    @Provides
    fun provideUiHelper(activity: Activity): UiHelper = UiHelper(activity)
}
```

```kotlin
@Module
class UserModule {
    @UserScope
    @Provides
    fun provideUiHelper(activity: Activity): UiHelper = UiHelper(activity)
}
```

4. create components
```kotlin
@Component(modules = [AppModule::class])
interface AppComponent {
    // ...
    fun getOrderComponent(): OrderComponent.OrderComponentBuilder
    fun getUserComponent(): UserComponent.UserComponentBuilder

}
```

```kotlin
@OrderScope
@Subcomponent(modules = [OrderModule::class])
interface OrderComponent {
    
    @Subcomponent.Builder
    interface OrderComponentBuilder {
        @BindsInstance
        fun activity(activity: Activity): OrderComponentBuilder
        fun build(): OrderComponent
    }

    fun getUiHelper(): UiHelper
    
}
```

```kotlin
@UserScope
@Subcomponent(modules = [UserModule::class])
interface UserComponent {
    
    @Subcomponent.Builder
    interface UserComponentBuilder {
        @BindsInstance
        fun activity(activity: Activity): UserComponentBuilder
        fun build(): UserComponent
    }
    
    fun getUiHelper(): UiHelper

}
```

5. create application
```kotlin
class App: Application() {
    val appComponent: AppComponent = DaggerAppComponent.create()
}
```

6. init objects in activities
```kotlin
class OrderActivity : ComponentActivity() {

    lateinit var orderComponent: OrderComponent
    lateinit var uiHelper: UiHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        orderComponent = (application as App).appComponent
            .getOrderComponent()
            .activity(this)
            .build()
        uiHelper = orderComponent.getUiHelper()
        
        //...
    }
}
```

```kotlin
class UserActivity : ComponentActivity() {
    lateinit var userComponent: UserComponent
    lateinit var uiHelper: UiHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        userComponent = (application as App).appComponent
            .getUserComponent()
            .activity(this)
            .build()
        uiHelper = userComponent.getUiHelper()
        //...
    }
}
```

## Common singleton object

1. create annotations
```kotlin
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class OrderScope
```

```kotlin
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class UserScope
```

```kotlin
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ActivityScope
```

2. create singleton object
```kotlin
@ActivityScope
class InjectUiHelper @Inject constructor(activity: Activity){
    //...
}
```

3. create components
```kotlin
@Component
interface AppComponent {
    // ...
    fun getOrderComponent(): OrderComponent.OrderComponentBuilder
    fun getUserComponent(): UserComponent.UserComponentBuilder

}
```

```kotlin
@ActivityScope
@OrderScope
@Subcomponent
interface OrderComponent {

    @Subcomponent.Builder
    interface OrderComponentBuilder {
        @BindsInstance
        fun activity(activity: Activity): OrderComponentBuilder
        fun build(): OrderComponent
    }
    
    fun getInjectUiHelper(): InjectUiHelper

}
```

```kotlin
@ActivityScope
@UserScope
@Subcomponent
interface UserComponent {

    @Subcomponent.Builder
    interface UserComponentBuilder {
        @BindsInstance
        fun activity(activity: Activity): UserComponentBuilder
        fun build(): UserComponent
    }

    fun getInjectUiHelper(): InjectUiHelper

}
```

4. create application
```kotlin
class App: Application() {
    val appComponent: AppComponent = DaggerAppComponent.create()
}
```

5. init in activities
```kotlin
class OrderActivity : ComponentActivity() {

    lateinit var orderComponent: OrderComponent
    lateinit var injectUiHelper: InjectUiHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        orderComponent = (application as App).appComponent
            .getOrderComponent()
            .activity(this)
            .build()
        injectUiHelper = orderComponent.getInjectUiHelper()
        //...
    }
}
```

```kotlin
@ActivityScope
@UserScope
@Subcomponent(modules = [UserModule::class])
interface UserComponent {

    @Subcomponent.Builder
    interface UserComponentBuilder {
        @BindsInstance
        fun activity(activity: Activity): UserComponentBuilder
        fun build(): UserComponent
    }

    fun getInjectUiHelper(): InjectUiHelper

}
```