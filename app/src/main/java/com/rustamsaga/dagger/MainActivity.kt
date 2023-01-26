package com.rustamsaga.dagger

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.rustamsaga.dagger.dependencies.ServerApi
import com.rustamsaga.dagger.ui.theme.DaggerTheme

class MainActivity : ComponentActivity() {

    // dagger-NAMED/004-1 - add field serverApi
    lateinit var serverApiAdmin: ServerApi
    lateinit var serverApiClient: ServerApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            // dagger-NAMED/005-1 - init fields
            serverApiAdmin = (application as App).appComponent.getServerApiAdmin()
            serverApiClient = (application as App).appComponent.getServerApiClient()

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
