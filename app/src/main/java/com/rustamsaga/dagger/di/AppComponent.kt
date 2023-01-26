package com.rustamsaga.dagger.di

import com.rustamsaga.dagger.SecondActivity
import com.rustamsaga.dagger.ThirdActivity
import com.rustamsaga.dagger.dependencies.ServerApi
import com.rustamsaga.dagger.di.modules.NetworkModule
import dagger.Component
import javax.inject.Named

// dagger-NAMED/003-1 - add getting function for ServerApi by @Named("...")
@Component(modules = [NetworkModule::class])
interface AppComponent {

    @Named("admin")
    fun getServerApiAdmin(): ServerApi

    @Named("client")
    fun getServerApiClient(): ServerApi

    // dagger-NAMED/003-2 - or we can use it:
    fun injectSecondActivity(secondActivity: SecondActivity)

    // dagger-QUALIFIER/004 - add inject fun
    fun injectThirdActivity(thirdActivity: ThirdActivity)


}