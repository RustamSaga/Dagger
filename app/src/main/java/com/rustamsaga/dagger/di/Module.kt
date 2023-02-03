package com.rustamsaga.dagger.di

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.content.res.Resources
import com.rustamsaga.dagger.MainActivityPresenter
import dagger.Module
import dagger.Provides

// dagger-passing object to the component/002 - create module with param context
@Module
class AppModule(private val context: Context) {

    @Provides
    fun provideContext(): Context {
        return context
    }

    @Provides
    fun providePreferences(): SharedPreferences {
        return context.getSharedPreferences("pref", MODE_PRIVATE)
    }

    @Provides
    fun provideResources(): Resources {
        return context.resources
    }
}

// dagger-passing object to the component/002 - create another module
@Module
class MainModule {
    @Provides
    fun provideMainActivityPresenter(context: Context): MainActivityPresenter {
        return MainActivityPresenter(context = context)
    }
}
