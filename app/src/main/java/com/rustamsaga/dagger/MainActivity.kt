package com.rustamsaga.dagger

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.rustamsaga.dagger.dependencies.DatabaseHelper
import com.rustamsaga.dagger.dependencies.NetworkUtils
import com.rustamsaga.dagger.di.AppComponent
import com.rustamsaga.dagger.ui.theme.DaggerTheme

class MainActivity : ComponentActivity() {

    // dagger/006 - init presenter
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
}
