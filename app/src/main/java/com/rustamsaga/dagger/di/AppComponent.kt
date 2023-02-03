package com.rustamsaga.dagger.di


import android.content.SharedPreferences
import android.content.res.Resources
import com.rustamsaga.dagger.MainActivityPresenter
import dagger.Component


// dagger-passing object to the component/003 - create Component
@Component(modules = [AppModule::class, MainModule::class])
interface AppComponent {

    fun getPref(): SharedPreferences
    fun getResources(): Resources

    fun getMainActivityPresenter(): MainActivityPresenter
}