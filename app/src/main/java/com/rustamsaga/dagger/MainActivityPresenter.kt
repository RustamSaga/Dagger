package com.rustamsaga.dagger

import com.rustamsaga.dagger.dependencies.DatabaseHelper
import com.rustamsaga.dagger.dependencies.NetworkUtils

// dagger/001 - create new class presenter (dependent)

class MainActivityPresenter(
    private val databaseHelper: DatabaseHelper,
    private val networkUtils: NetworkUtils
) {
    // ...
}