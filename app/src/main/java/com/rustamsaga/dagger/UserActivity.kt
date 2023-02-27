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
import com.rustamsaga.dagger.di.OrderComponent
import com.rustamsaga.dagger.di.UserComponent
import com.rustamsaga.dagger.ui.theme.DaggerTheme

class UserActivity : ComponentActivity() {

    lateinit var userComponent: UserComponent
    lateinit var appDatabase: AppDatabase
    lateinit var uiHelper: UiHelper
    lateinit var injectUiHelper: InjectUiHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // dagger-008/ init userComponent
        userComponent = (application as App).appComponent
            .getUserComponent()
            .activity(this)
            .build()
        appDatabase = userComponent.getAppDatabase()
        uiHelper = userComponent.getUiHelper()
        injectUiHelper = userComponent.getInjectUiHelper()

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