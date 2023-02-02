package com.rustamsaga.dagger.di


import com.rustamsaga.dagger.MainActivity
import com.rustamsaga.dagger.MainActivityPresenter
import com.rustamsaga.dagger.NetworkUtils
import dagger.Component


// dagger-Annotation Inject/002 - get Presenter without module class
@Component(modules = [MainModule::class])
interface AppComponent {

    fun getMainActivityPresenter(): MainActivityPresenter

    fun getNetworkUtils(): NetworkUtils
}