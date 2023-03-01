# Hilt. Under the hood

For ease of use, I added TODO filter. To follow links you need to create filters - click to `TODO`
in bottom panel -> icon
filter ![image](https://user-images.githubusercontent.com/121166010/214673108-b36497d7-85a4-4086-8beb-c6e8dbe297ad.png)
-> Edit Filters -> add pattern `\bdagger\b.*` and filter `dagger`

### this example in compose, but we can use in activity and fragment

## We have three classes

```kotlin
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DaggerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val mContext = LocalContext.current
                    Column(
                        modifier = Modifier
                            .fillMaxHeight(0.5f)
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.SpaceAround,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Button(onClick = {
                            mContext.startActivity(Intent(mContext, OrderActivity::class.java))
                        }) {
                            Text(text = "OrderActivity") 
                        }
                        Button(onClick = {
                            mContext.startActivity(Intent(mContext, UserActivity::class.java))
                        }) {
                            Text(text = "UserActivity")
                        }
                    }
                }
            }
        }
    }
}
```

 And we have two classes for orderActivity
```kotlin
class OrderActivity : HiltOrderActivity() {

    @Inject
    lateinit var repository: OrderRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState) // repository will be initialized here

        Log.d("OrderActivity", "repository = $repository")

        setContent {
            DaggerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background,
                ) {
                    Text(text = "OrderActivity")
                }
            }
        }
    }
}
```

```kotlin
open class HiltOrderActivity : ComponentActivity() {

    lateinit var activityComponent: HiltActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent = (application as App).appComponent.getActivityComponent()
        activityComponent.injectOrderActivity(this as OrderActivity)
        
    }
}
```

And we have two classes for userActivity
```kotlin
class UserActivity : HiltUserActivity() {

    @Inject
    lateinit var repository: UserRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState) // repository will be initialized here

        Log.d("UserActivity", "repository = $repository")

        setContent {
            DaggerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Text(text = "UserActivity")
                }
            }
        }
    }
}
```

```kotlin
open class HiltUserActivity : ComponentActivity() {
    lateinit var activityComponent: HiltActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityComponent = (application as App).appComponent.getActivityComponent()
        activityComponent.injectUserActivity(this as UserActivity)
    }
}
```

## App - superclass
```kotlin
class App: HiltApp() {

    @Inject
    lateinit var databaseHelper: DatabaseHelper

    override fun onCreate() {
        super.onCreate() // run inject
        Log.d("HiltApp", "databaseHelper = $databaseHelper")
    }

}
```
* superclass for App. Extracts the HiltAppComponent getter code from the App class
```kotlin
open class HiltApp: Application() {

    val appComponent = DaggerHiltAppComponent.create()

    override fun onCreate() {
        super.onCreate()
        appComponent.injectApp(this as App)
    }
}
```

## Components

* AppComponent - injects objects to the App, and create ActivityComponent
```kotlin
@Component
interface HiltAppComponent {

    fun injectApp(app: App)

    fun getActivityComponent(): HiltActivityComponent
}
```

* ActivityComponent - Dagger subcomponent for injected objects in Activity classes. Is common to all Activity. It also creates FragmentComponent.
```kotlin
@Subcomponent
interface HiltActivityComponent {
    fun injectOrderActivity(activity: OrderActivity)
    fun injectUserActivity(userActivity: UserActivity)
}
```
## Other classes
```kotlin
class DatabaseHelper @Inject constructor()

class OrderRepository @Inject constructor()

class UserRepository @Inject constructor()
```