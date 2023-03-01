package com.rustamsaga.dagger

import android.app.Application
import android.util.Log
import com.rustamsaga.dagger.di.DaggerHiltAppComponent
import javax.inject.Inject


class App: HiltApp() {

    @Inject
    lateinit var databaseHelper: DatabaseHelper

    override fun onCreate() {
        super.onCreate() // run inject
        Log.d("HiltApp", "databaseHelper = $databaseHelper")
    }

}

open class HiltApp: Application() {

    val appComponent = DaggerHiltAppComponent.create()

    override fun onCreate() {
        super.onCreate()
        appComponent.injectApp(this as App)
    }
}
