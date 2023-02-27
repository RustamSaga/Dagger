package com.rustamsaga.dagger.di


import android.app.Activity
import dagger.BindsInstance
import dagger.Component

// dagger-004/ create appComponent and add @AppScope and get methods for subcomponents by builder
@AppScope
@Component(modules = [AppModule::class])
interface AppComponent {
    // ...

    fun getOrderComponent(): OrderComponent.OrderComponentBuilder

    fun getUserComponent(): UserComponent.UserComponentBuilder

}

