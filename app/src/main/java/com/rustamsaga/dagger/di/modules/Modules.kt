package com.rustamsaga.dagger.di.modules

import com.rustamsaga.dagger.dependencies.Admin
import com.rustamsaga.dagger.dependencies.Client
import com.rustamsaga.dagger.dependencies.LocalServer
import com.rustamsaga.dagger.dependencies.ServerApi
import dagger.Module
import dagger.Provides
import javax.inject.Named

// dagger-NAMED/002 - Create two different ServerApi (admin and client) in the module
//  also we need to add 2 annotation @Named - one for admin second for client
@Module
class NetworkModule {

    @Named("admin")
    @Provides
    fun provideServerApiAdmin(): ServerApi = ServerApi("admin.server.com")

    @Named("client")
    @Provides
    fun provideServerApiClient(): ServerApi = ServerApi("client.server.com")


    // dagger-QUALIFIER/003 - create provider functions by your annotations (@Admin and @Client)
    @Admin
    @Provides
    fun provideLocalServerAdmin(): LocalServer = LocalServer("admin-001")

    @Client("001")
    @Provides
    fun provideLocalServerClient001(): LocalServer = LocalServer("client/001")

    @Client("002")
    @Provides
    fun provideLocalServerClient002(): LocalServer = LocalServer("client/002")

}