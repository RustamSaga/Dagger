package com.rustamsaga.dagger

import android.app.Application
import com.rustamsaga.dagger.di.AppComponent
import com.rustamsaga.dagger.di.AppModule
import com.rustamsaga.dagger.di.DaggerAppComponent

// dagger-passing object to the component/004 - create AppComponent by use Builder directly
//  to put in the Module some object, to which dagger2 doesn't have an access
class App: Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()
    }
}
