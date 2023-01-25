# Dagger mini-tutorial

For ease of use, I added TODO filter. To follow links you need to create filters -
 click to `TODO` in bottom panel -> icon filter ![image](https://user-images.githubusercontent.com/121166010/214673108-b36497d7-85a4-4086-8beb-c6e8dbe297ad.png) -> Edit Filters -> add pattern `\bdagger\b.*` and filter `dagger` 



# Dagger 2- Annotations - [more details here](https://blog.canopas.com/dagger-2-annotation-b3a27d53dabf).
* [@Inject](https://github.com/RustamSaga/Dagger/blob/master/README.md#inject)
* [@Module](https://github.com/RustamSaga/Dagger/blob/master/README.md#module)
* [@Provides](https://github.com/RustamSaga/Dagger/blob/master/README.md#provides)
* [@Binds](https://github.com/RustamSaga/Dagger/blob/master/README.md#binds)
* [@Component](https://github.com/RustamSaga/Dagger/blob/master/README.md#component)

## `@Inject`
the `@Inject` annotation will tell the Dagger what all the dependencies needed to be transferred to the dependent.

Can be used in:
* _`constructor`_

```
class LoginPresenter @Inject constructor(
     private val userService: UserService,
     private val userManager: UserManager) 
: BasePresenter<View>{ ... }
```

* _`field`_
```ini
class LoginActivity : AppCompatActivity(){
     @Inject lateinit var loginPresenter: LoginPresenter
}
```
* _`method`_
```ini
lateinit var user: User
@Inject
internal fun loadFromPreferences() {
   user = userPreferences.user
}
```

## `@Module`
`@Module` annotation, marks the modules/classes whose methods provide dependencies.
 ```ini
@Module
class NetworkModule {
    @Provides
    fun provideNetworkUtils(): NetworkModule {
        return NetworkModule()
    }
}
```

## `@Provides`
This annotation is used in `@Module` and how we want to design and provide dependence.
 ```ini
@Module
class NetworkModule {
    @Provides
    fun provideNetworkUtils(): NetworkModule {
        return NetworkModule()
    }
}
```


## `@Binds`
`@Binds` is equivalent to `@Provides`. It is mainly used to bind interfaces and abstract classes.
```ini
@Module
abstract class MainActivityModule {
@Binds
abstract fun bindMainPresenter(
         presenter: MainPresenter): LoginContract.Presenter
}
```

## `@Component`
bridge between `@Inject` and `@Module`
```ini
@Component(modules = [StorageModule::class, NetworkModule::class])
interface AppComponent {
 ...
}
```
