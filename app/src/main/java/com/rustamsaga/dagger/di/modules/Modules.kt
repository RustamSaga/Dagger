package com.rustamsaga.dagger.di.modules

import com.rustamsaga.dagger.MainActivityPresenter
import com.rustamsaga.dagger.dependencies.ConnectionManager
import com.rustamsaga.dagger.dependencies.DatabaseHelper
import com.rustamsaga.dagger.dependencies.NetworkUtils
import com.rustamsaga.dagger.dependencies.Repository
import dagger.Module
import dagger.Provides

// dagger/004 - add provide fun for presenter
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


// dagger/003 - add provide fun for NetworkUtils and DatabaseHelper
@Module
class NetworkModule {
    @Provides
    fun provideNetworkUtils(
        connectionManager: ConnectionManager
    ): NetworkUtils = NetworkUtils(connectionManager)
}

@Module
class StorageModule {
    @Provides
    fun provideStorageHelper(
        repository: Repository
    ): DatabaseHelper = DatabaseHelper(repository)
}