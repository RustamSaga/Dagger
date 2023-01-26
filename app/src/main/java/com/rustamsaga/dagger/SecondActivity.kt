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
import com.rustamsaga.dagger.dependencies.ServerApi
import com.rustamsaga.dagger.ui.theme.DaggerTheme
import javax.inject.Inject
import javax.inject.Named

class SecondActivity : ComponentActivity() {

    // dagger-NAMED/004-2 - add field with annotate @Named("...")
    @Inject
    @Named("client")
    lateinit var serverApiClient: ServerApi

    @Inject
    @Named("admin")
    lateinit var serverApiAdmin: ServerApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            // dagger-NAMED/005-2 - init fields
            (application as App).appComponent.injectSecondActivity(this)

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
