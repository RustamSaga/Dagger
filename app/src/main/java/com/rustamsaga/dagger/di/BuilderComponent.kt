package com.rustamsaga.dagger.di

import com.rustamsaga.dagger.MainActivityPresenter
import dagger.Component

// dagger-004/ create another component by dependencies
@Component(modules = [MainModule::class], dependencies = [AppComponent::class])
interface BuilderComponent {
    fun getMainActivityPresenter(): MainActivityPresenter

}

