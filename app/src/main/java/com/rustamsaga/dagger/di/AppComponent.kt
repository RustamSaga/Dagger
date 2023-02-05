package com.rustamsaga.dagger.di


import com.rustamsaga.dagger.InjectActivity
import dagger.Component

@Component(modules = [InjectModule::class])
interface AppComponent {
    // ...

// dagger-getMethod/004 - create main component, and a function getter for subcomponent.builder or subcomponent.factory
    fun getBuilderComponentBuilder(): BuilderComponent.Builder
    fun getFactoryComponentFactory(): FactoryComponent.Factory

    // dagger-injectMethod/004 - or use this way
    fun injectInjectActivity(injectActivity: InjectActivity)
}

