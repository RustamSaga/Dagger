package com.rustamsaga.dagger

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import com.rustamsaga.dagger.di.HiltActivityComponent
import com.rustamsaga.dagger.ui.theme.DaggerTheme
import javax.inject.Inject

class OrderActivity : HiltOrderActivity() {

    @Inject
    lateinit var repository: OrderRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("OrderActivity", "repository = $repository")

        setContent {
            DaggerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    Text(text = "OrderActivity", fontSize = 32.sp)

                }
            }
        }
    }
}


open class HiltOrderActivity : ComponentActivity() {

    lateinit var activityComponent: HiltActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent = (application as App).appComponent.getActivityComponent()
        activityComponent.injectOrderActivity(this as OrderActivity)

    }
}

