package com.rustamsaga.dagger.di

import com.rustamsaga.dagger.AppDatabaseHelperWithModule
import com.rustamsaga.dagger.AppNetworkUtilsWithoutModule
import com.rustamsaga.dagger.SubcomOrderRepositoryWithoutModule
import com.rustamsaga.dagger.SubcomOrderRepositoryWithModule
import dagger.Subcomponent
import javax.inject.Scope

// dagger-005/ create subcomponent and add @OrderScope
@OrderScope
@Subcomponent(modules = [OrderModule::class])
interface OrderComponent {
    // by inject annotation
    fun getOrderRepository(): SubcomOrderRepositoryWithoutModule

    fun getOrderRepositoryByProvidesMethod(): SubcomOrderRepositoryWithModule

    fun getAppDatabaseHelper(): AppDatabaseHelperWithModule

    fun getAppNetworkUtils(): AppNetworkUtilsWithoutModule

}


// dagger-001/ create two annotations first for subcomponent and second for appComponent
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class OrderScope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class AppScope
