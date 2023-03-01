package com.rustamsaga.dagger.di

import androidx.lifecycle.ViewModel
import com.rustamsaga.dagger.*
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class ViewModelModule {

    @IntoMap
    @ViewModelKey(ViewModel1::class)
    @Provides
    fun provideViewModel1(networkUtils: NetworkUtils): ViewModel {
        return ViewModel1(networkUtils)
    }

    @IntoMap
    @ViewModelKey(ViewModel2::class)
    @Provides
    fun provideViewModel2(databaseHelper: DatabaseHelper): ViewModel2 {
        return ViewModel2(databaseHelper)
    }
}