package com.rustamsaga.dagger.di


import com.rustamsaga.dagger.MainActivity
import com.rustamsaga.dagger.SecondActivity
import com.rustamsaga.dagger.TestElementsIntoSet
import com.rustamsaga.dagger.TestIntoMap
import com.rustamsaga.dagger.di.modules.ElementsIntoSetModule
import com.rustamsaga.dagger.di.modules.IntoMapModule
import com.rustamsaga.dagger.di.modules.SetModule
import dagger.Component


// dagger-IntoSet-ElementsIntoSet-IntoMap/004 - add fun for giving set<EventHandler>
@Component(modules = [
    SetModule::class,
    ElementsIntoSetModule::class,
    IntoMapModule::class
])
interface AppComponent {

    val testElementsIntoSet: TestElementsIntoSet

    fun injectMainActivity(mainActivity: MainActivity)

    val testIntoMap: TestIntoMap

    fun injectSecondActivity(secondActivity: SecondActivity)

}