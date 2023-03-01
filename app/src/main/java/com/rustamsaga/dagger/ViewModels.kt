package com.rustamsaga.dagger

import androidx.lifecycle.ViewModel
import dagger.MapKey
import javax.inject.Inject
import kotlin.reflect.KClass

class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository
)

class ViewModel1(val networkUtils: NetworkUtils): ViewModel()
class ViewModel2(val databaseHelper: DatabaseHelper): ViewModel()

class DatabaseHelper {}


@MapKey
@Retention(AnnotationRetention.RUNTIME)
annotation class ViewModelKey(val value: KClass<out ViewModel>)