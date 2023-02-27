package com.rustamsaga.dagger

import com.rustamsaga.dagger.di.AppScope
import com.rustamsaga.dagger.di.OrderScope
import javax.inject.Inject

// dagger-002 - create singleton objects for example two singletons appComponent and two subcomponent
@AppScope
class AppDatabaseHelperWithModule {
    fun showMessage(message: String): String {
        return "DatabaseHelper: $message"
    }
}

@AppScope
class AppNetworkUtilsWithoutModule @Inject constructor(){
    fun showMessage(message: String): String {
        return "NetworkHelper: $message"
    }
}


@OrderScope
class SubcomOrderRepositoryWithoutModule @Inject constructor(){
    //...
}

@OrderScope
class SubcomOrderRepositoryWithModule {
    //...
}