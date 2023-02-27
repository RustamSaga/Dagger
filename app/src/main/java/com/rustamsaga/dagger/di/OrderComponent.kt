package com.rustamsaga.dagger.di

import android.app.Activity
import com.rustamsaga.dagger.AppDatabase
import com.rustamsaga.dagger.InjectUiHelper
import com.rustamsaga.dagger.UiHelper
import dagger.BindsInstance
import dagger.Subcomponent

// dagger-005/ create OrderComponent and add two annotation - @ActivityScope and @OrderScope
@ActivityScope
@OrderScope
@Subcomponent(modules = [OrderModule::class])
interface OrderComponent {

    // dagger-005/ and then create custom builder for provide activity
    @Subcomponent.Builder
    interface OrderComponentBuilder {
        @BindsInstance
        fun activity(activity: Activity): OrderComponentBuilder
        fun build(): OrderComponent
    }

    fun getAppDatabase(): AppDatabase

    fun getUiHelper(): UiHelper

    fun getInjectUiHelper(): InjectUiHelper

}
