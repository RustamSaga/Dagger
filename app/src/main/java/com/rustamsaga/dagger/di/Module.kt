package com.rustamsaga.dagger.di

import com.rustamsaga.dagger.DatabaseHelper
import com.rustamsaga.dagger.MainActivityPresenter
import com.rustamsaga.dagger.NetworkUtils
import dagger.Module
import dagger.Provides


// dagger-001/ dependencies module for appComponent. MainComponent does not know about this module
@Module
class DependenciesModule {

    @Provides
    fun provideDatabaseHelper(): DatabaseHelper = DatabaseHelper()

    @Provides
    fun provideNetworkUtils(): NetworkUtils = NetworkUtils()
}

// dagger-002/ this module for MainComponent. AppComponent does not know about this module
@Module
class MainModule {
    @Provides
    fun provideMainActivityPresenter(
        databaseHelper: DatabaseHelper,
        networkUtils: NetworkUtils
    ): MainActivityPresenter {
        return MainActivityPresenter(databaseHelper, networkUtils)
    }
}

