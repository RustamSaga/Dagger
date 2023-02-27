package com.rustamsaga.dagger.di

import com.rustamsaga.dagger.AppDatabaseHelperWithModule
import com.rustamsaga.dagger.SubcomOrderRepositoryWithModule
import dagger.Module
import dagger.Provides


// dagger-003/ create modules for appComponent and subcomponent
@Module
class AppModule {
    @AppScope
    @Provides
    fun provideDatabaseHelper(): AppDatabaseHelperWithModule = AppDatabaseHelperWithModule()
}


@Module
class OrderModule {
    @OrderScope
    @Provides
    fun provideOrderRepositoryByProvidesMethod(): SubcomOrderRepositoryWithModule {
        return SubcomOrderRepositoryWithModule()
    }
}

