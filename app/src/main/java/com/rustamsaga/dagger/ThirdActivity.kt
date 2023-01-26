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
import com.rustamsaga.dagger.dependencies.Admin
import com.rustamsaga.dagger.dependencies.Client
import com.rustamsaga.dagger.dependencies.LocalServer
import com.rustamsaga.dagger.ui.theme.DaggerTheme
import javax.inject.Inject

class ThirdActivity : ComponentActivity() {

    // dagger-QUALIFIER/005 - add fields
    @Inject
    @Admin
    lateinit var localServerAdmin: LocalServer

    @Inject
    @Client("001")
    lateinit var localServerClient001: LocalServer

    @Inject
    @Client("002")
    lateinit var localServerClient002: LocalServer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            // dagger-QUALIFIER/006 - init
            (application as App).appComponent.injectThirdActivity(this)

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
