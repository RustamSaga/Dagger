package com.rustamsaga.dagger

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.rustamsaga.dagger.di.OrderComponent
import com.rustamsaga.dagger.ui.theme.DaggerTheme

class OrderActivity : ComponentActivity() {

    lateinit var orderComponent: OrderComponent
    lateinit var appDatabase: AppDatabase
    lateinit var uiHelper: UiHelper
    lateinit var injectUiHelper: InjectUiHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // dagger-008/ init orderComponent
        orderComponent = (application as App).appComponent
            .getOrderComponent()
            .activity(this)
            .build()
        appDatabase = orderComponent.getAppDatabase()
        uiHelper = orderComponent.getUiHelper()
        injectUiHelper = orderComponent.getInjectUiHelper()


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