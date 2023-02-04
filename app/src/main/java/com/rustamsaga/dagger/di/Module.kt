package com.rustamsaga.dagger.di

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.content.res.Resources
import com.rustamsaga.dagger.DatabaseHelper
import com.rustamsaga.dagger.MainActivityPresenter
import com.rustamsaga.dagger.NetworkUtils
import dagger.Module
import dagger.Provides

// dagger/002 - create module for presenter (used in Subcomponent)
@Module
class MainModule {
    @Provides
    fun provideMainActivityPresenter(databaseHelper: DatabaseHelper, networkUtils: NetworkUtils): MainActivityPresenter {
        return MainActivityPresenter(databaseHelper, networkUtils)
    }
}

