package com.rustamsaga.dagger

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

// dagger-005/ create App with annotation @HiltAndroidApp
@HiltAndroidApp
class App: Application() {

    @Inject
    lateinit var databaseHelper: DatabaseHelper

    override fun onCreate() {
        super.onCreate()
        Log.d("App", "database = $databaseHelper")
    }
}
