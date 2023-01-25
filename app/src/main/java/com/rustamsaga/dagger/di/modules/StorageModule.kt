package com.rustamsaga.dagger.di.modules

import com.rustamsaga.dagger.dependencies.DatabaseHelper
import dagger.Module
import dagger.Provides

@Module
class StorageModule {

    @Provides
    fun provideStorageHelper(): DatabaseHelper {
        return DatabaseHelper()
    }
}