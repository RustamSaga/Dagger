package com.rustamsaga.dagger.di

import android.app.Activity
import com.rustamsaga.dagger.InjectActivity
import com.rustamsaga.dagger.MainActivityPresenter
import dagger.BindsInstance
import dagger.Subcomponent

// dagger-getMethod/003 - create subcomponent with module, and custom builder with activity
@Subcomponent(modules = [MainModule::class])
interface BuilderComponent {

    @Subcomponent.Builder
    interface Builder {
        @BindsInstance fun activity(activity: Activity): Builder
        fun build(): BuilderComponent
    }

    fun getMainActivityPresenter(): MainActivityPresenter

}

// dagger-getMethod/003 - create subcomponent with module, and factory
@Subcomponent(modules = [MainModule::class])
interface FactoryComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance activity: Activity): FactoryComponent
    }

    fun getMainActivityPresenter(): MainActivityPresenter
}


// dagger-injectMethod/003 - create subcomponent, and custom builder or factory
@Subcomponent
interface InjectComponent {

    // you can use builder or factory
    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance activity: Activity): InjectComponent
    }

    fun injectInjectActivity(injectActivity: InjectActivity)
}
