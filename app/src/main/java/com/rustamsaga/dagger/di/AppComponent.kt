package com.rustamsaga.dagger.di


import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import com.rustamsaga.dagger.DatabaseHelper
import com.rustamsaga.dagger.NetworkUtils
import dagger.BindsInstance
import dagger.Component

// dagger/003 - create main component, and create a function getter for subcomponent
@Component
interface AppComponent {

    fun getNetworkUtils(): NetworkUtils
    fun getDatabaseHelper(): DatabaseHelper

    fun getSecondComponent(): SecondComponent
}

