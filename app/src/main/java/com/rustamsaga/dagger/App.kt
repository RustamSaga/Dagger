package com.rustamsaga.dagger

import android.app.Application
import com.rustamsaga.dagger.di.AppComponent
import com.rustamsaga.dagger.di.BuilderComponent
import com.rustamsaga.dagger.di.DaggerAppComponent
import com.rustamsaga.dagger.di.DaggerBuilderComponent

class App: Application() {
    val appComponent: AppComponent = DaggerAppComponent.create()

    // dagger-005/ init builderComponent
    val builderComponent: BuilderComponent =
        DaggerBuilderComponent
            .builder()
            .appComponent(appComponent)
            .build()
}
