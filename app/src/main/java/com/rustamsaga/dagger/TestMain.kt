package com.rustamsaga.dagger

import com.rustamsaga.dagger.di.DaggerAppComponent
import com.rustamsaga.dagger.di.SecondComponent

fun main() {

    val appComponent = DaggerAppComponent.create()

    println(appComponent.getDatabaseHelper().showMessage("main function"))
    println(appComponent.getNetworkUtils().showMessage("main function"))

    presenterMessages(appComponent.getSecondComponent())
}


fun presenterMessages(subcomponent: SecondComponent) {

    val presenter = subcomponent.getMainActivityPresenter()

    println(presenter.databaseHelper.showMessage("from presenter"))
    println(presenter.networkUtils.showMessage("from presenter"))

}