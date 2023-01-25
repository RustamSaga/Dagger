package com.rustamsaga.dagger.dependencies


class DatabaseHelper(private val repository: Repository) {}

class NetworkUtils(private val connectionManager: ConnectionManager) {}

// dagger/002 - create new dependency classes and add them to upper class constructors
class Repository {}

class ConnectionManager{}