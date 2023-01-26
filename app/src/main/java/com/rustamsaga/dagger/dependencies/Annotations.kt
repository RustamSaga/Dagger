package com.rustamsaga.dagger.dependencies

import javax.inject.Qualifier

// dagger-QUALIFIER/001 - create annotation classes

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Admin

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Client(val value: String = "")

// dagger-QUALIFIER/002 - create another class for example:
class LocalServer(private val host: String)