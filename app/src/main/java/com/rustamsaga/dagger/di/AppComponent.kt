package com.rustamsaga.dagger.di


import com.rustamsaga.dagger.DatabaseHelper
import com.rustamsaga.dagger.NetworkUtils
import dagger.Component

// dagger-003/ create common appComponent
@Component(modules = [DependenciesModule::class])
interface AppComponent {
    // ...

    // must have for create another object in another components
    fun getDatabaseHelper(): DatabaseHelper
    fun getNetworkUtils(): NetworkUtils

}

