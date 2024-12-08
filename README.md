# KPlayerDemo

A song list demo based on Kotlin and Jetpack Compose. Features include:
- Display a song list from [iTunes Search API](https://itunes.apple.com/search?term=Talyor+Swift&limit=200&media=music).
- Accept user input as keyword for searching by song name and album name.
- Provide 2 sorting options: by song name and by album name, in ascending order
- **Offline mode**. When the App cannot reach iTunes server, a cached list will be loaded from the loacal database.
- **Refresh** the list by clicking the refresh button on the top right corner.

## Quick Start
The demo requires at minimum Java 8+ or Android API 29+ (Android 10.0+).

The project could be directly opened in Android Studio. After Gradle sync automatically, click "Run" and the app could launch on your device or emulator.

## Technical Highlights
### MVVM Architecture
The app applies MVVM architecture as basic concept, as well as taking Google's [Guide to app architecture](https://developer.android.com/topic/architecture) in Android Developer website, and CLEAN architecture into consideration.

The project structure is organized as below:

Data layer:
```
data
├── local
│   ├── dao
│   │   └── SongDao.kt
│   └── SongDatabase.kt
├── remote
│   ├── dto
│   │   ├── Result.kt
│   │   └── SongListResponse.kt
│   └── SearchAPI.kt
└── repository               // Actual impletation of repository
    └── SongRepositoryImpl.kt
```
Domain layer:
```
domain
├── model
│   └── Song.kt
├── repository                // Interface of repository only
│   └── SongRepository.kt
└── use_case                  // Business logic that unit tests should cover
    ├── GetAllSongsUseCase.kt
    ├── SearchSongsUseCase.kt
    └── UpdateSongsUseCase.kt
```
UI layer:
```
ui
├── component
│   └── SongListItem.kt
├── SongListScreen.kt      // UI elements
├── SongListState.kt
└── SongListViewModel.kt   // State holder
```
### Dependency Injection
[Dagger-Hilt](https://developer.android.com/training/dependency-injection/hilt-android) is used for dependency management of this project.
### More Hints
- **Coroutine and StateFlow** for mapping, filter and sorting
- **Jetpack Compose** for UI
- **JUnit4** for unit test
