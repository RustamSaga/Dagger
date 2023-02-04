package com.rustamsaga.dagger.di

import com.rustamsaga.dagger.MainActivityPresenter
import dagger.Component
import dagger.Subcomponent

// dagger/004 - create subcomponent with module
@Subcomponent(modules = [MainModule::class])
interface SecondComponent {

    fun getMainActivityPresenter(): MainActivityPresenter

}