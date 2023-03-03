# Hilt. Basis

For ease of use, I added TODO filter. To follow links you need to create filters - click to `TODO`
in bottom panel -> icon
filter ![image](https://user-images.githubusercontent.com/121166010/214673108-b36497d7-85a4-4086-8beb-c6e8dbe297ad.png)
-> Edit Filters -> add pattern `\bdagger\b.*` and filter `dagger`

## Connection

in build.gradle project
```groovy
plugins {
    //...
    id 'com.google.dagger.hilt.android' version '2.44' apply false
}
```
in build.gradle module
```groovy
    plugins {
    //...
    id 'kotlin-kapt'
    id 'dagger.hilt.android.plugin'
}
//...
dependencies {
    //...
    implementation "com.google.dagger:hilt-android:2.44"
    kapt "com.google.dagger:hilt-compiler:2.44"
}
kapt {
    correctErrorTypes true
}
```

## App superclass
```kotlin
@HiltAndroidApp
class App: Application() {

    @Inject
    lateinit var databaseHelper: DatabaseHelper

    override fun onCreate() {
        super.onCreate()
        Log.d("App", "database = $databaseHelper")
    }
}
```

## Objects
```kotlin
class DatabaseHelper @Inject constructor()

class Repository @Inject constructor()
```

## Activity
```kotlin
@AndroidEntryPoint
class Activity : ComponentActivity() {

    @Inject
    lateinit var repository: Repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("Activity", "repository = $repository")

        //...
    }
}
```
