package com.rustamsaga.dagger

import android.app.Application
import com.rustamsaga.dagger.di.AppComponent
import com.rustamsaga.dagger.di.DaggerAppComponent

// dagger-007/ init appComponent
class App: Application() {
    val appComponent: AppComponent = DaggerAppComponent.create()
}
