package com.rustamsaga.dagger.di

import android.app.Activity
import com.rustamsaga.dagger.AppDatabase
import com.rustamsaga.dagger.InjectUiHelper
import com.rustamsaga.dagger.UiHelper
import dagger.BindsInstance
import dagger.Subcomponent

// dagger-006/ create UserComponent and add two annotation - @ActivityScope and @OrderScope
@ActivityScope
@UserScope
@Subcomponent(modules = [UserModule::class])
interface UserComponent {

    // dagger-006/ and then create custom builder for provide activity
    @Subcomponent.Builder
    interface UserComponentBuilder {
        @BindsInstance
        fun activity(activity: Activity): UserComponentBuilder
        fun build(): UserComponent
    }

    fun getAppDatabase(): AppDatabase

    fun getUiHelper(): UiHelper

    fun getInjectUiHelper(): InjectUiHelper

}