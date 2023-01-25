package com.rustamsaga.dagger

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.rustamsaga.dagger.dependencies.ConnectionManager
import com.rustamsaga.dagger.dependencies.DatabaseHelper
import com.rustamsaga.dagger.dependencies.NetworkUtils
import com.rustamsaga.dagger.ui.theme.DaggerTheme

class MainActivity : ComponentActivity() {

    // dagger/002 - variable declaration dagger.Lazy<T>
    lateinit var connectionManagerLazy: dagger.Lazy<ConnectionManager>
    lateinit var databaseHelperLazy: dagger.Lazy<DatabaseHelper>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            connectionManagerLazy = (application as App).appComponent.getConnectionManager()
            databaseHelperLazy = (application as App).appComponent.getDatabaseHelper()

            DaggerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    // dagger/003 - creating ConnectionManager and DatabaseHelper
                    var networkUtils: ConnectionManager? = null
                    var databaseHelper: DatabaseHelper? = null
                    Button(onClick = {
                        networkUtils = connectionManagerLazy.get()
                        databaseHelper = databaseHelperLazy.get()
                    }) {}
                    /** All subsequent get calls will no longer create new NetworkUtils objects,
                        but return the one that was created on the first get call. */

                }
            }
        }
    }
}
