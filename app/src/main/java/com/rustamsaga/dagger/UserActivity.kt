package com.rustamsaga.dagger

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.rustamsaga.dagger.ui.theme.DaggerTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

// dagger-006/ add annotation for activity
@AndroidEntryPoint
class UserActivity : ComponentActivity() {

    @Inject
    lateinit var repository: UserRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("UserActivity", "repository = $repository")

        setContent {
            DaggerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Box(modifier = Modifier.fillMaxSize(0.1f)){
                        Text(text = "UserActivity", fontSize = 32.sp)
                    }

                }
            }
        }
    }
}