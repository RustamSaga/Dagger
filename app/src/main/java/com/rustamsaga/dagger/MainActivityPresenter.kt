package com.rustamsaga.dagger

import javax.inject.Inject
import javax.inject.Named

// dagger-Annotation Inject/001 - create Presenter and other dependencies for this class
//  and add annotation @Inject for constructor

// Not added to @Module
class MainActivityPresenter @Inject constructor(
    private val localDatabase: LocalDatabase,
    @Named("dev") val serverDev: Server,
    @Named("client") val serverClient: Server
) {
    // ...
    override fun toString(): String {
        return "Presenter: $localDatabase, $serverDev, $serverClient"
    }

    // When the dagger2 creates this (MainActivityPresenter,
    // it will call postInit immediately after creation.
    @Inject
    fun postInit(networkUtils: NetworkUtils){
        // ...
    }
}

// Not added to @Module
class NetworkUtils @Inject constructor() {
    override fun toString(): String = "NetworkUtils"
}

// Not added to @Module
class LocalDatabase @Inject constructor() {
    override fun toString(): String = "LocalDatabase"
}

class Server(private val route: String) {
    override fun toString(): String = route
}

