package com.rustamsaga.dagger.di.modules

import com.rustamsaga.dagger.MainActivityPresenter
import com.rustamsaga.dagger.dependencies.ConnectionManager
import com.rustamsaga.dagger.dependencies.DatabaseHelper
import com.rustamsaga.dagger.dependencies.NetworkUtils
import com.rustamsaga.dagger.dependencies.Repository
import dagger.Module
import dagger.Provides

// dagger/000 - create LazyModule
@Module
class LazyModule {
    @Provides
    fun provideLazyConnectionManager(): ConnectionManager = ConnectionManager()

    @Provides
    fun provideRepository(): Repository = Repository()

    @Provides
    fun provideNetworkUtils(repository: Repository): DatabaseHelper {
        return DatabaseHelper(Repository())
    }
}