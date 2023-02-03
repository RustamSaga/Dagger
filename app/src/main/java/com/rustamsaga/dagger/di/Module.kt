package com.rustamsaga.dagger.di

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.content.res.Resources
import dagger.Module
import dagger.Provides

@Module
class AppModule() {

    // // context takes from our custom builder
    @Provides
    fun providePreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences("pref", MODE_PRIVATE)
    }

    @Provides
    fun provideResources(context: Context): Resources {
        return context.resources
    }
}

