package com.rustamsaga.dagger.di

import com.rustamsaga.dagger.Server
import dagger.Module
import dagger.Provides
import javax.inject.Named


@Module
class MainModule {

    @Provides
    @Named("dev")
    fun provideServerDev(): Server {
        return Server("dev.server.com")
    }

    @Provides
    @Named("client")
    fun provideServerClient(): Server {
        return Server("client.server.com")
    }

}