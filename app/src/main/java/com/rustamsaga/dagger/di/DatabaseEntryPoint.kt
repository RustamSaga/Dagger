package com.rustamsaga.dagger.di

import com.rustamsaga.dagger.DatabaseHelper
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent


@EntryPoint
@InstallIn(ActivityComponent::class)
interface DatabaseEntryPoint {
    fun getDatabaseHelper(): DatabaseHelper
}