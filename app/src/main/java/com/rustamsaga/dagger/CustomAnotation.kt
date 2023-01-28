package com.rustamsaga.dagger

import dagger.MapKey

enum class EventType {
    TELEGRAM, LOGGER
}

@MapKey
@Retention(AnnotationRetention.RUNTIME)
annotation class EventKey(val key: EventType)