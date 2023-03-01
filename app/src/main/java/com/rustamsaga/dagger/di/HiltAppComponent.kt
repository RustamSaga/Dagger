package com.rustamsaga.dagger.di

import com.rustamsaga.dagger.App
import com.rustamsaga.dagger.OrderActivity
import com.rustamsaga.dagger.UserActivity
import dagger.Component
import dagger.Subcomponent

@Component
interface HiltAppComponent {

    fun injectApp(app: App)

    fun getActivityComponent(): HiltActivityComponent
}

@Subcomponent
interface HiltActivityComponent {
    fun injectOrderActivity(activity: OrderActivity)
    fun injectUserActivity(userActivity: UserActivity)
}