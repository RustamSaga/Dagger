package com.rustamsaga.dagger

import android.app.Activity
import javax.inject.Inject

// dagger-GetMethod/001 - create some objects (add object activity in constructor)
class MainActivityPresenter(
    val databaseHelper: DatabaseHelper,
    val networkUtils: NetworkUtils,
    val activity: Activity
) {}

class DatabaseHelper @Inject constructor() {
    fun showMessage(message: String): String {
        return "DatabaseHelper: $message"
    }
}

class NetworkUtils @Inject constructor() {
    fun showMessage(message: String): String {
        return "NetworkHelper: $message"
    }
}

// dagger-InjectMethod/001 - create some objects (add object activity in constructor)
class InjectActivityPresenter(
    val databaseHelper: DatabaseHelper,
    val networkUtils: NetworkUtils,
    val activity: Activity
)