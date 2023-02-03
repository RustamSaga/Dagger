# Dagger mini-tutorial

For ease of use, I added TODO filter. To follow links you need to create filters -
 click to `TODO` in bottom panel -> icon filter ![image](https://user-images.githubusercontent.com/121166010/214673108-b36497d7-85a4-4086-8beb-c6e8dbe297ad.png) -> Edit Filters -> add pattern `\bdagger\b.*` and filter `dagger` 

## `@Named` 
Sometimes we need to create several objects of the same class, but with different inputs.

#### Module
```kotlin
    @Named("admin")
    @Provides
    fun provideServerApiAdmin(): ServerApi = ServerApi("admin.server.com")

    @Named("client")
    @Provides
    fun provideServerApiClient(): ServerApi = ServerApi("client.server.com")
```

#### Component
```kotlin
   fun injectActivity(activity: Activity)
```
or 
```kotlin
    @Named("admin")
    fun getServerApiAdmin(): ServerApi

    @Named("client")
    fun getServerApiClient(): ServerApi
```

#### Activity
```kotlin

// if use "fun injectActivity(activity: Activity)" in @Component you need add these annotations
    @Inject
    @Named("admin")
    lateinit var serverApiAdmin: ServerApi 

    @Inject
    @Named("client")
    lateinit var serverApiClient: ServerApi
   
```

## `@Qualifier`

#### Creating and using your annotations
```kotlin
    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class Admin
 
    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class Client(val value: String = "")
```

#### Module
```kotlin
    @Admin
    @Provides
    fun provideLocalServerAdmin(): LocalServer = LocalServer("admin-001")

    @Client("001")
    @Provides
    fun provideLocalServerClient001(): LocalServer = LocalServer("client/001")

    @Client("002")
    @Provides
    fun provideLocalServerClient002(): LocalServer = LocalServer("client/002")
```

#### Component

```kotlin
   fun injectActivity(activity: Activity)
```
or
```kotlin
   @Admin
   fun getLocalServerAdmin(): LocalServer("admin")
   
   @Client("001")
   fun getLocalServerClient001(): LocalServer("client/001")
   
   @Client("002")
   fun getLocalServerClient002(): LocalServer("client/002")
```


#### Activity

```kotlin

// if use "fun injectActivity(activity: Activity)" in @Component you need add these annotations
    @Inject
    @Admin
    lateinit var localServerAdmin: LocalServer

    @Inject
    @Client("001")
    lateinit var localServerClient001: LocalServer

    @Inject
    @Client("002")
    lateinit var localServerClient002: LocalServer
```
