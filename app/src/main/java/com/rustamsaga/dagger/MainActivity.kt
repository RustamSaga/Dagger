package com.rustamsaga.dagger

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.rustamsaga.dagger.di.AppComponent
import com.rustamsaga.dagger.di.LoginComponent
import com.rustamsaga.dagger.ui.theme.DaggerTheme

class MainActivity : ComponentActivity() {

    lateinit var appComponent: AppComponent
    lateinit var loginComponent: LoginComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        appComponent = (application as App).appComponent
        loginComponent = appComponent.loginComponent()

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
