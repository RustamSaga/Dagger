package com.rustamsaga.dagger.di.modules

import com.rustamsaga.dagger.dependencies.NetworkUtils
import dagger.Module
import dagger.Provides

@Module
class NetworkModule {

    @Provides
    fun provideNetworkUtils(): NetworkUtils {
        return NetworkUtils()
    }
}