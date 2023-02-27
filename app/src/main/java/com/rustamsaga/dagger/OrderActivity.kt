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

class OrderActivity: ComponentActivity() {

    // dagger-007/ getting singleton objects
    lateinit var orderComponent: OrderComponent
    lateinit var orderRepository: SubcomOrderRepositoryWithoutModule
    lateinit var orderRepositoryWithProvidesMethod: SubcomOrderRepositoryWithModule
    lateinit var appNetworkUtilsWithInjectAnnotation: AppNetworkUtilsWithoutModule
    lateinit var appDatabaseHelperWithProvidesMethod: AppDatabaseHelperWithModule

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        orderComponent = (application as App).appComponent.getOrderComponent()
        orderRepository = orderComponent.getOrderRepository()
        orderRepositoryWithProvidesMethod = orderComponent.getOrderRepositoryByProvidesMethod()
        appNetworkUtilsWithInjectAnnotation = orderComponent.getAppNetworkUtils()
        appDatabaseHelperWithProvidesMethod = orderComponent.getAppDatabaseHelper()


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