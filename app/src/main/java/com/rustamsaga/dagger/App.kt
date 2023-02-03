package com.rustamsaga.dagger

import android.app.Application
import com.rustamsaga.dagger.di.AppComponent
import com.rustamsaga.dagger.di.AppModule
import com.rustamsaga.dagger.di.DaggerAppComponent

class App: Application() {
    lateinit var appComponent: AppComponent

    // dagger-Builder/002 - use custom builder and call our method
    override fun onCreate() {
        super.onCreate()
//        appComponent = DaggerAppComponent
//            .builder()
//            .context(this)
//            .buildAppComp() // getting AppComponent

        // or dagger-Builder/002 - use custom builder and call our method
        appComponent = DaggerAppComponent
            .factory()
            .create(this, AppModule())
    }
}
