package com.rustamsaga.dagger.di

import android.app.Activity
import com.rustamsaga.dagger.AppDatabase
import com.rustamsaga.dagger.UiHelper
import dagger.Module
import dagger.Provides

// dagger-003/ create module for appComponent and add @AppScope for provide method
@Module
class AppModule() {
    @AppScope
    @Provides
    fun provideAppDatabase(): AppDatabase = AppDatabase()
}


// dagger-003/ create module for orderComponent and add @OrderScope for provide method
@Module
class OrderModule {

    @OrderScope
    @Provides
    fun provideUiHelper(activity: Activity): UiHelper = UiHelper(activity)

}

// dagger-003/ create module for userComponent and add @UserScope for provide method
@Module
class UserModule {

    @UserScope
    @Provides
    fun provideUiHelper(activity: Activity): UiHelper = UiHelper(activity)

}
