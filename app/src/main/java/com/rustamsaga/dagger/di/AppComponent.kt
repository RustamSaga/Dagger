package com.rustamsaga.dagger.di


import com.rustamsaga.dagger.DatabaseHelper
import com.rustamsaga.dagger.NetworkUtils
import dagger.Component

// dagger-002/ create common appComponent
@Component(modules = [AppModule::class])
interface AppComponent {
    // ...
    fun getOrderComponent(): OrderComponent

}

