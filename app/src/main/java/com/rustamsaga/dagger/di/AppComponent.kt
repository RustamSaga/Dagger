package com.rustamsaga.dagger.di


import com.rustamsaga.dagger.AppDatabaseHelperWithModule
import com.rustamsaga.dagger.AppNetworkUtilsWithoutModule
import dagger.Component

// dagger-004/ create appComponent and add @AppScope
@AppScope
@Component(modules = [AppModule::class])
interface AppComponent {
    // ...
    fun getOrderComponent(): OrderComponent

    fun getAppDatabaseHelper(): AppDatabaseHelperWithModule

    fun getAppNetworkUtils(): AppNetworkUtilsWithoutModule

}

