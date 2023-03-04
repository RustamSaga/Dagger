package com.rustamsaga.dagger

import android.app.Activity
import android.app.Application
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

// dagger-001/ create any class
class NetworkUtils (app: Application, activity: Activity)

class DatabaseHelper (app: Application)

@Singleton // single for all classes, all activities
class Room @Inject constructor()

// dagger
@ActivityScoped // single just for one activity
class Repository @Inject constructor()

//@FragmentScoped // single just for one fragment
//class FragmentClass
