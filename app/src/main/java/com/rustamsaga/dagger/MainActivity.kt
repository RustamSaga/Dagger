package com.rustamsaga.dagger

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.rustamsaga.dagger.ui.theme.DaggerTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    // dagger-Annotation Inject/003

    lateinit var presenter: MainActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            presenter = (application as App).appComponent.getMainActivityPresenter()

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

    // dagger-Annotation Inject/003
    // When the dagger2 creates this (MainActivityPresenter,
    // it will call postInit immediately after creation.
    @Inject
    fun postInit(networkUtils: NetworkUtils) {
        // ...
    }
}
