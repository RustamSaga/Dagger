package com.rustamsaga.dagger.di

import com.rustamsaga.dagger.DatabaseHelper
import com.rustamsaga.dagger.MainActivityPresenter
import com.rustamsaga.dagger.NetworkUtils
import dagger.Module
import dagger.Provides


// dagger-001/ create modules for appComponent and subcomponent
@Module
class AppModule {

    @Provides
    fun provideDatabaseHelper(): DatabaseHelper = DatabaseHelper()

    @Provides
    fun provideNetworkUtils(): NetworkUtils = NetworkUtils()
}

@Module
class PresenterModule {
    @Provides
    fun provideMainActivityPresenter(
        databaseHelper: DatabaseHelper,
        networkUtils: NetworkUtils
    ): MainActivityPresenter {
        return MainActivityPresenter(databaseHelper, networkUtils)
    }
}

