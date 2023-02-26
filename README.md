# Component life cycle

For ease of use, I added TODO filter. To follow links you need to create filters - click to `TODO`
in bottom panel -> icon
filter ![image](https://user-images.githubusercontent.com/121166010/214673108-b36497d7-85a4-4086-8beb-c6e8dbe297ad.png)
-> Edit Filters -> add pattern `\bdagger\b.*` and filter `dagger`

### AppComponent
```kotlin
@Component(modules = [AppModule::class])
interface AppComponent {
    // ...
    fun getOrderComponent(): OrderComponent

}
```

### SubComponent
```kotlin
@Subcomponent(modules = [PresenterModule::class])
interface OrderComponent {
    fun getMainActivityPresenter(): MainActivityPresenter

}
```

### OrderActivity (second activity)
```kotlin
class OrderActivity: ComponentActivity() {

    lateinit var orderComponent: OrderComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        orderComponent = (application as App).appComponent.getOrderComponent()
        //...
    }
}
```

### Notes
#### 1
AppComponent will continue to live after the OrderActivity is closed.
It is stored in the Application object and is not tied to life any Activity.

The next time we run the OrderActivity screen and take the AppComponent from the Application object,
it will be exactly the same component as the last time.

#### 2
Every time we call the getOrderComponent method in the component, we will receive a new 
OrderComponent. The appComponent does not hold the link to the subcomponent 
and does not control it. It creates it, gives it to us and forgets about it.

Thus, the component’s task is only to create a subcomponent (and to give modules).
And the life time of the subcomponent is determined by us. We store this subcomponent for as long 
as we need, and then release it to the garbage collector to destroy it.

No manual destruction of components or sub-components is performed.

#### 3
These two examples show that the life time of the subcomponent is less than that of the component. 
During the lifetime of the appComponent (application time) passes several life cycles of 
the subcomponent (opening/closing the screen).

#### 4
In Activity.onCreate we call the subcomponent code after calling super.onCreate. 
But it can be done before. In Lesson 14 we consider the case of subcomponent passing
from Activity to fragment. In this case, the subcomponent must be obtained before the super.onCreate.

