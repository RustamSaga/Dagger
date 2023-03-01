package com.rustamsaga.dagger.di


import com.rustamsaga.dagger.ViewModelFactory
import dagger.Component

// dagger-004/ create component and getter method
@Component(modules = [ViewModelModule::class])
interface AppComponent {

    fun getViewModelFactory(): ViewModelFactory
    fun loginComponent(): LoginComponent


}