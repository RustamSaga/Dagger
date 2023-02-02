package com.rustamsaga.dagger

import com.rustamsaga.dagger.di.DaggerAppComponent
import javax.inject.Inject

fun main() {
    val component = DaggerAppComponent.create()
    val presenter = component.getMainActivityPresenter()
    println(presenter.toString())

    val networkUtils = component.getNetworkUtils()
    println(postInit(networkUtils))
}

fun postInit(networkUtils: NetworkUtils) : String{
    return networkUtils.toString()
}