package com.rustamsaga.dagger.di

import com.rustamsaga.dagger.MainActivityPresenter
import com.rustamsaga.dagger.dependencies.DatabaseHelper
import com.rustamsaga.dagger.dependencies.NetworkUtils
import com.rustamsaga.dagger.di.modules.MainModule
import com.rustamsaga.dagger.di.modules.NetworkModule
import com.rustamsaga.dagger.di.modules.StorageModule
import dagger.Component

// dagger/005 - add MainModule in `modules` and create get function for presenter
@Component(modules = [StorageModule::class, NetworkModule::class, MainModule::class])
interface AppComponent {

    fun getMainActivityPresenter(): MainActivityPresenter
}