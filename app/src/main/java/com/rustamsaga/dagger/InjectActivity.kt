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
import com.rustamsaga.dagger.di.AppComponent
import com.rustamsaga.dagger.di.InjectComponent
import com.rustamsaga.dagger.ui.theme.DaggerTheme
import javax.inject.Inject

class InjectActivity : ComponentActivity() {

    // dagger-injectMethod/005 - using inject method

    @Inject
    lateinit var injectComponentFactory: InjectComponent.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (application as App).appComponent.injectInjectActivity(this)
        val injectComponent = injectComponentFactory.create(this)

        setContent {
            DaggerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {}
            }
        }
    }
}
