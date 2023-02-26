package com.rustamsaga.dagger.di

import com.rustamsaga.dagger.MainActivityPresenter
import dagger.Component
import dagger.Subcomponent

// dagger-003/ create subcomponent
@Subcomponent(modules = [PresenterModule::class])
interface OrderComponent {
    fun getMainActivityPresenter(): MainActivityPresenter

}

