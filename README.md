# Subcomponent

For ease of use, I added TODO filter. To follow links you need to create filters - click to `TODO`
in bottom panel -> icon
filter ![image](https://user-images.githubusercontent.com/121166010/214673108-b36497d7-85a4-4086-8beb-c6e8dbe297ad.png)
-> Edit Filters -> add pattern `\bdagger\b.*` and filter `dagger`

#### Subcomponents allow to divide the functionality of the component into several parts.

#### Benefits.

1. The logical division of functions. Different components - different responsibilities.

2. More flexible transfer of objects into components.

3. More flexible lifetime of objects returned by components

## First benefit example

### Objects
```kotlin
class MainActivityPresenter(
    val databaseHelper: DatabaseHelper,
    val networkUtils: NetworkUtils
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

### Module
```kotlin
class MainModule {
    @Provides
    fun provideMainActivityPresenter(databaseHelper: DatabaseHelper, networkUtils: NetworkUtils): MainActivityPresenter {
        return MainActivityPresenter(databaseHelper, networkUtils)
    }
}
```

### Base component
```kotlin
@Component
interface AppComponent {

    fun getNetworkUtils(): NetworkUtils
    fun getDatabaseHelper(): DatabaseHelper

    fun getSecondComponent(): SecondComponent
}
```

### Subcomponent
```kotlin
@Subcomponent(modules = [MainModule::class])
interface SecondComponent {

    fun getMainActivityPresenter(): MainActivityPresenter

}
```

### Test 
```kotlin
fun main() {

    val appComponent = DaggerAppComponent.create()

    println(appComponent.getDatabaseHelper().showMessage("main function"))
    println(appComponent.getNetworkUtils().showMessage("main function"))

    presenterMessages(appComponent.getSecondComponent())
}


fun presenterMessages(subcomponent: SecondComponent) {

    val presenter = subcomponent.getMainActivityPresenter()

    println(presenter.databaseHelper.showMessage("from presenter"))
    println(presenter.networkUtils.showMessage("from presenter"))

}
```