package com.rustamsaga.dagger.dependencies


class DatabaseHelper(private val repository: Repository) {}

class NetworkUtils(private val connectionManager: ConnectionManager) {}

class Repository {}

class ConnectionManager{}