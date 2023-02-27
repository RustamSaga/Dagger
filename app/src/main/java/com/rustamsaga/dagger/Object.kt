package com.rustamsaga.dagger

import android.app.Activity
import com.rustamsaga.dagger.di.ActivityScope
import com.rustamsaga.dagger.di.AppScope
import com.rustamsaga.dagger.di.OrderScope
import javax.inject.Inject


// dagger-002/ create object for provide in module. Does not require annotation
class UiHelper(activity: Activity){
    //...
}

// dagger-002/ create object. This is global for any subcomponent
@AppScope // this class is a shared object for all subcomponents
class AppDatabase() {
    //...
}

// dagger-002/ create object that will be used without the module. And will be common to subcomponents
@ActivityScope
class InjectUiHelper @Inject constructor(activity: Activity){
    //...
}

