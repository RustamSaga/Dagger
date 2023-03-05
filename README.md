# Hilt. Components

For ease of use, I added TODO filter. To follow links you need to create filters - click to `TODO`
in bottom panel -> icon
filter ![image](https://user-images.githubusercontent.com/121166010/214673108-b36497d7-85a4-4086-8beb-c6e8dbe297ad.png)
-> Edit Filters -> add pattern `\bdagger\b.*` and filter `dagger`

## SingletonComponent
It is appComponent. And lifecycle = lifecycle application

In order for Hilt to create a SingletonComponent, we need to use the @HiltAndroidApp annotation on the application class.
```kotlin
@HiltAndroidApp
class App : Application() {
    //...
}
```
* Component creation and object injection will be performed in Application.onCreate.
* Scope annotation: @Singleton
* Available objects: Application

## ActivityRetainedComponent
ActivityRetainedComponent stores objects for ActivityComponent to survive screen rotation
 To use this component we need add annotation @AndroidEntryPoint for activity class
```kotlin
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    //...
}
```
this annotation creates ActivityRetainedComponent and activityComponent.
And we use @ActivityRetainedScope for singleton of activity whose object survives the rotation of the screen
```kotlin
@ActivityRetainedScoped
class Repository @Inject constructor()
```

* Available objects: Application

## ServiceComponent
This component is for injecting an object into a Service. And its life cycle = service life cycle
```kotlin
@AndroidEntryPoint
class MainService : Service() {
    // ...
}
```

* Component creation and object injection will be done in onCreate.
* Scope annotation: @ServiceScoped
* Available objects: Application and Service


## ActivityComponent
This component is for injecting an object into a Activity. And its lifecycle = activity lifecycle
```kotlin
@AndroidEntryPoint
class OrderActivity : ComponentActivity() {
    // ...
}
```

* Component creation and object injection will be done in onCreate.
* Scope annotation: @ActivityScoped
* Available objects: Application and Activity

## ViewModelComponent
This component is for injecting an object into a ViewModel. 
And its lifecycle = viewModel lifecycle
```kotlin
@HiltViewModel
class MainViewModel @Inject constructor(...): ViewModel() {
    // ...
}
```
and getting model in module by @Provides
```kotlin
@Module
@InstallIn(ActivityComponent::class)
class ActivityModule {
    @Provides
    fun provideMainViewModel(
        databaseHelper: DatabaseHelper,
        networkUtils: NetworkUtils
    ): MainViewModel {
        return MainViewModel(databaseHelper, networkUtils)
    }
}
```

* Scope annotation: @ViewModelScoped
* Available objects: Application and SavedStateHandle

Note: ViewModelComponent is extending ActivityRetainedComponent
Which means ViewModelComponent has access to single ActivityRetainedComponent objects
* One viewModel - one singleton object just for it - we use @ViewModelScoped
* If we need common singleton object for all ViewModels - we use @ActivityRetainedScoped

## FragmentComponent
This component is for injecting an object into a Fragment.
And its lifecycle = Fragment lifecycle
```kotlin
@AndroidEntryPoint
class MainFragment : Fragment() {
    // ...
}
```

* Component creation and object injection will be done in onAttach.
* Scope annotation: @FragmentScoped
* Available objects: Application, Activity and Fragment


## ViewComponent
This component is for injecting an object into a View.
And its lifecycle = View lifecycle
```kotlin
@AndroidEntryPoint
class OrderView(context: Context, attrs: AttributeSet): AppCompatTextView(context, attrs) {
    // ...
}
```

* Component creation and object injection will be done when called function super().
* Scope annotation: @ViewScoped
* Available objects: Application, Activity and View


## ViewWithFragmentComponent
Same as previous component, but this component use parent as FragmentComponent
```kotlin
@WithFragmentBindings
@AndroidEntryPoint
class MainView(context: Context, attrs: AttributeSet): AppCompatTextView(context, attrs) {
    // ...
}
```

* Component creation and object injection will be done when called function super().
* Scope annotation: @ViewScoped
* Available objects: Application, Activity, Fragment and View


# Conclusion
* if we need SingletonComponent (AppComponent) - we use @HiltAndroidApp for App class
* if we need ActivityComponent - we use @AndroidEntryPoint for Activity class
* if we need ServiceComponent - we use @AndroidEntryPoint for Service class
* if we need ViewModelComponent - we use @HiltViewModel for ViewModel class
* if we need FragmentComponent - we use @AndroidEntryPoint for Fragment class
* if we need ViewComponent - we use @AndroidEntryPoint for View class
* if we need ViewWithFragmentComponent - we use @WithFragmentBindings and @@AndroidEntryPoint for View class

* if we need any object as singleton, we use annotation scope. By choosing the scope annotation for an object, we determine the lifecycle of the object. 
- singleton for all objects - @Singleton
- singleton for all viewModels - @ActivityRetainedScoped
- singleton for one viewModel - @ViewModelScoped
- singleton for service - @ServiceScoped
- singleton for activity - @ActivityScoped
- singleton for fragment - @FragmentScoped
- singleton for view - @ViewScoped


