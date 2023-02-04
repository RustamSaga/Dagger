package com.rustamsaga.dagger

import javax.inject.Inject

// dagger/001 - create some objects
class MainActivityPresenter(
    val databaseHelper: DatabaseHelper,
    val networkUtils: NetworkUtils
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