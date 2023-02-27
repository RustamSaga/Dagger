package com.rustamsaga.dagger.di

import javax.inject.Scope

// dagger-001/ this annotation for singleton objects just for OrderComponent (subcomponent)
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class OrderScope

// dagger-001/ this annotation for singleton objects just for UserComponent (subcomponent)
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class UserScope

// dagger-001/ this annotation for global singleton objects, because used in AppComponent
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class AppScope

// dagger-001/ this annotation for singleton objects that are shared by orderComponent and userComponent
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ActivityScope


