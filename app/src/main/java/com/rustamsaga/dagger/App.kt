package com.rustamsaga.dagger

import android.app.Application
import com.rustamsaga.dagger.di.AppComponent
import com.rustamsaga.dagger.di.DaggerAppComponent


class App: Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }
}
