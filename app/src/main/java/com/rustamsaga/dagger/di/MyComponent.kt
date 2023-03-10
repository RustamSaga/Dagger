package com.rustamsaga.dagger.di

import com.rustamsaga.dagger.MyRepository
import dagger.hilt.DefineComponent
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton

@MyScope
@DefineComponent(parent = SingletonComponent::class)
interface MyComponent  {
    @DefineComponent.Builder
    interface MyComponentBuilder {
        fun build(): MyComponent
    }
}

@InstallIn(MyComponent::class)
@EntryPoint
interface MyEntryPoint {
    fun getMyRepository(): MyRepository
}

@Singleton
class MyComponentManager @Inject constructor(
    val myComponentBuilder: MyComponent.MyComponentBuilder
){
    private var myComponent: MyComponent? = null

    fun create() {
        myComponent = myComponentBuilder.build()
    }

    fun get() = myComponent

    fun destroy() {
        myComponent = null
    }
}