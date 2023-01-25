package com.rustamsaga.dagger.di

import com.rustamsaga.dagger.dependencies.ConnectionManager
import com.rustamsaga.dagger.dependencies.DatabaseHelper
import com.rustamsaga.dagger.dependencies.NetworkUtils
import com.rustamsaga.dagger.di.modules.LazyModule
import dagger.Component

// dagger/001 - Wrap ConnectionManager and DatabaseHelper in Lazy.
//  (can only use one, this code is only an example for two different dependencies)
@Component(modules = [LazyModule::class])
interface AppComponent {

    fun getConnectionManager(): dagger.Lazy<ConnectionManager>
    fun getDatabaseHelper(): dagger.Lazy<DatabaseHelper>
}