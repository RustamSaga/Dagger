package com.rustamsaga.dagger.di

import com.rustamsaga.dagger.dependencies.DatabaseHelper
import com.rustamsaga.dagger.dependencies.NetworkUtils
import com.rustamsaga.dagger.di.modules.NetworkModule
import com.rustamsaga.dagger.di.modules.StorageModule
import dagger.Component

// dagger/005 - create @Component - intermediary between the order and the client
@Component(modules = [StorageModule::class, NetworkModule::class])
interface AppComponent {

    fun getDatabaseHelper(): DatabaseHelper
    fun getNetworkUtils(): NetworkUtils

    // alternative - but you must add annotations @Inject in fields mainActivity
    // fun injectMainActivity(mainActivity: MainActivity)
}