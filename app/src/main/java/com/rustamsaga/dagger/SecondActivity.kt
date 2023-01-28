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

class SecondActivity : ComponentActivity() {

    // dagger-IntoMap/005 - create field Map<String, @JvmSuppressWildcards AnalyticsTracker>
    @Inject
    lateinit var mapAnalyticsTracker: Map<EventType, @JvmSuppressWildcards AnalyticsTracker>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {


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
