package com.rustamsaga.dagger

import com.rustamsaga.dagger.di.MyScope
import javax.inject.Inject

class DatabaseHelper @Inject constructor() {}

@MyScope
class MyRepository @Inject constructor()