package com.rustamsaga.dagger

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.rustamsaga.dagger.di.AppComponent
import com.rustamsaga.dagger.ui.theme.DaggerTheme

class MainActivity : ComponentActivity() {

    lateinit var appComponent: AppComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // dagger-getMethod/005 - create builderComponent with builder
        appComponent = (application as App).appComponent
        val builderComponent = appComponent.getBuilderComponentBuilder()
            .activity(this)
            .build()

        // or dagger-getMethod/005 - create factoryComponent with factory
        val factoryComponent = appComponent.getFactoryComponentFactory().create(this)

        setContent {
            DaggerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                }
            }
        }
    }
}
