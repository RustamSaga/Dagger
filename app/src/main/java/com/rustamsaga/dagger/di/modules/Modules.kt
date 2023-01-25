package com.rustamsaga.dagger.di.modules

import com.rustamsaga.dagger.dependencies.DatabaseHelper
import com.rustamsaga.dagger.dependencies.NetworkUtils
import dagger.Module
import dagger.Provides

// dagger/004 - create modules (or module) for provide our classes

@Module
class NetworkModule {

    @Provides
    fun provideNetworkUtils(): NetworkUtils {
        return NetworkUtils()
    }
}


@Module
class StorageModule {

    @Provides
    fun provideStorageHelper(): DatabaseHelper {
        return DatabaseHelper()
    }
}