package com.rustamsaga.dagger

import android.app.Activity
import javax.inject.Inject

class MainActivityPresenter(
    val databaseHelper: DatabaseHelper,
    val networkUtils: NetworkUtils
) {}

class DatabaseHelper {
    fun showMessage(message: String): String {
        return "DatabaseHelper: $message"
    }
}

class NetworkUtils {
    fun showMessage(message: String): String {
        return "NetworkHelper: $message"
    }
}


class InjectActivityPresenter(
    val databaseHelper: DatabaseHelper,
    val networkUtils: NetworkUtils,
    val activity: Activity
)