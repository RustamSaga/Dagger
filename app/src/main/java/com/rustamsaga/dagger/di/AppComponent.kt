package com.rustamsaga.dagger.di


import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import dagger.BindsInstance
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {

    fun getSharedPref(): SharedPreferences
    fun getRecourses(): Resources

    // dagger-Builder/001 - create custom @Component.Builder
//    @Component.Builder
//    interface AppCompBuilder {
//        fun buildAppComp(): AppComponent  // must have
//        @BindsInstance  // to avoid calling the context in the module (AppModule) - this way better than writing context in a module
//        fun context(context: Context): AppCompBuilder
//    }

    // or dagger-Factory/001 - create factory instead of building
    @Component.Factory
    interface AppCompFactory {
        fun create(@BindsInstance context: Context, appModule: AppModule): AppComponent
    }


}

