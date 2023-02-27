package com.rustamsaga.dagger.di

import com.rustamsaga.dagger.MainActivityPresenter
import dagger.Component

// dagger-004/ create component and getter method
@Component
interface AppComponent {

    fun getMainActivityPresenter(): MainActivityPresenter
}