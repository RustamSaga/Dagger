# Hilt. EntryPoint

For ease of use, I added TODO filter. To follow links you need to create filters - click to `TODO`
in bottom panel -> icon
filter ![image](https://user-images.githubusercontent.com/121166010/214673108-b36497d7-85a4-4086-8beb-c6e8dbe297ad.png)
-> Edit Filters -> add pattern `\bdagger\b.*` and filter `dagger`

## EntryPoint interface

```kotlin
@EntryPoint
@InstallIn(ActivityComponent::class)
interface DatabaseEntryPoint {
    fun getDatabaseHelper(): DatabaseHelper
}
```

* or

```kotlin
@EntryPoint
@InstallIn(SingletonComponent::class) // or other components
interface DatabaseEntryPoint {
    fun getDatabaseHelper(): DatabaseHelper
}
```

## Activity

```kotlin
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val entryPoint = EntryPoints.get(this, DatabaseEntryPoint::class.java)
        val databaseHelper = entryPoint.getDatabaseHelper()
        //...
    }
}
```

* or if you used singletonComponent

```kotlin
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val entryPoint = EntryPoints.get(application, DatabaseEntryPoint::class.java)
        val databaseHelper = entryPoint.getDatabaseHelper()
        //...
    }
}
```

## Another method
```kotlin
@EntryPoint
@InstallIn(ActivityComponent::class)
interface DataEntryPoint {
    fun injectMyConnection(myConnection: MyConnection)
}
```

```kotlin
class MyConnection: Connection {
 
    @Inject
    lateinit var databaseHelper: DatabaseHelper
 
    override fun onConnect() {}
 
    override fun onDisconnect() {}
 
}
```