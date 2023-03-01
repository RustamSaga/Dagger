package com.rustamsaga.dagger

import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import javax.inject.Inject

// dagger-001/ create some objects
class NetworkUtils @Inject constructor() {
    override fun toString(): String {
        return "NetworkUtils"
    }
}

class UserRepository @Inject constructor()
