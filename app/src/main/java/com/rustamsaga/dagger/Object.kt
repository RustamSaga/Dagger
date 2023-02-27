package com.rustamsaga.dagger

import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import javax.inject.Inject

// dagger-001/ create some objects
class NetworkUtils @Inject constructor() {
    override fun toString(): String {
        return "NetworkUtils"
    }
}

// dagger-002/ create object with @AssistedInject - this object create when we need
data class ServiceApi @AssistedInject constructor(
    val networkUtils: NetworkUtils,
    @Assisted val id: Int,
    @Assisted("host") val host: String,  // Assisted have param, because we have two value String
    @Assisted("port") val port: String
) {}

// dagger-003/ create a class where serviceApi is required, but we use serviceApiFactory
data class MainActivityPresenter @Inject constructor(
    private val serviceApiFactory: ServiceApiFactory){

    @AssistedFactory
    interface ServiceApiFactory {
        fun create(
            id: Int,
            @Assisted("host") host: String,
            @Assisted("port") port: String = "80"
        ): ServiceApi
    }

    // Here we create serviceApi
    fun onCreate(id: Int, host: String, port: String = "80"): ServiceApi {
        return serviceApiFactory.create(id, host, port)
    }

    // Or like this
    val serviceApi = serviceApiFactory.create(1, "test.drive.inside")

}
