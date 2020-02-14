# Android Client for Boomset Events

* Android Architecture Components: 
    ViewModel , 
    LiveData, 
    Paging Library, 
    Room Database Library
    
* RxAndroid2: Asynch Operations
* Retrofit: Network Operations
* OkHttp: Network Operations
* RxBus: Communication
* Boomset Api: oAuth and get events list and guest list of selected event 
* Moshi:  JSON Library

## Features
* Showing events list and guest list of selected event with token
* Pagination while scrolling
* Room and Sqllite db used for offline support
* Seperate UI, business logic and data sources' responsibilities
* Avoids multi-threading problems
* Testable 

## Approach of Clean Architecture for Android
There are 3 layers in the project: Data (DB and Network), Entity and  UI layer that contains view and viewModels.

## Requirements &amp; configurations
#### Requirements
- JDK 8
- Android SDK API 28
- Kotlin Gradle plugin 1.3.61 *(it will be installed automatically when this project is synced)*

#### Configurations
- minSdkVersion=21
- targetSdkVersion=28

## Language
*   [Kotlin](https://kotlinlang.org/)

## Libraries
*   [AndroidX](https://developer.android.com/jetpack/androidx)
*   [RxJava2](https://github.com/ReactiveX/RxJava/wiki/What's-different-in-2.0)
*   [RxAndroid](https://github.com/ReactiveX/RxAndroid)
*   [OkHttp](http://square.github.io/okhttp/)
*   [Retrofit](http://square.github.io/retrofit/)
*   [Gson](https://github.com/google/gson)
*   [Moshi](https://github.com/square/moshi)



## More about The Clean Architecture

[The Clean Architecture](https://8thlight.com/blog/uncle-bob/2012/08/13/the-clean-architecture.html)

