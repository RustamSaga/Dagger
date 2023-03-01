package com.rustamsaga.dagger.di

import androidx.fragment.app.Fragment
import com.rustamsaga.dagger.MainActivity
import dagger.Subcomponent

@Subcomponent
interface LoginComponent {

    fun inject(activity: Fragment)
}