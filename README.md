# KMP Task Manager

A cross-platform Task Manager application built with Kotlin Multiplatform (KMP) demonstrating stable KMP technologies for mobile development.

## Technologies Used

| Technology | Purpose |
|------------|---------|
| **Room** | Local SQLite database with type-safe queries |
| **Koin** | Dependency injection framework |
| **Ktor** | HTTP client for networking (with mock API demo) |
| **Compose Multiplatform** | Shared UI across Android and iOS |
| **Navigation Compose** | Type-safe navigation with animated transitions |
| **Kotlinx Serialization** | JSON serialization for network responses |
| **Kotlinx Datetime** | Cross-platform date/time handling |
| **Kotlinx Coroutines** | Asynchronous programming with Flow |

## Module Architecture

The project is structured into modular layers that can be integrated incrementally:

```
┌──────────────────────────────────────────────────────────────┐
│                      :composeApp                             │
│         (UI Layer: Screens, ViewModels, Navigation, DI)      │
├──────────────────────────────────────────────────────────────┤
│                        :core:data                            │
│               (Repository implementations)                    │
├─────────────────────────┬────────────────────────────────────┤
│     :core:database      │          :core:network             │
│   (Room: Entities,      │      (Ktor: HttpClient,            │
│    DAOs, Database)      │       Mock API, DTOs)              │
├─────────────────────────┴────────────────────────────────────┤
│                       :core:model                            │
│              (Domain models: Task, Category, Priority)       │
└──────────────────────────────────────────────────────────────┘
```

### Module Descriptions

| Module | Description | Dependencies |
|--------|-------------|--------------|
| `:core:model` | Pure Kotlin domain models (Task, Category, Priority). No platform dependencies. | kotlinx-datetime |
| `:core:database` | Room database layer with entities, DAOs, and platform-specific builders. | :core:model, Room |
| `:core:network` | Ktor HTTP client with mock API service for demonstrating network calls. | :core:model, Ktor |
| `:core:data` | Repository pattern implementations combining local and remote data sources. | :core:database, :core:network |
| `:composeApp` | Main application with UI (screens, ViewModels), navigation, and Koin DI setup. | All core modules |

### Integration Order for Training

Participants can integrate the modules in this order:

1. **Start with `:core:model`** - Define the domain models
2. **Add `:core:database`** - Implement local persistence with Room
3. **Add `:core:network`** - Set up networking with Ktor
4. **Add `:core:data`** - Create repositories combining data sources
5. **Complete with `:composeApp`** - Build the UI and wire everything together

## Project Structure

```
kmp-training/
├── core/
│   ├── model/                    # Domain models
│   │   └── src/commonMain/kotlin/
│   │       └── de/inovex/kmp_training/core/model/
│   │           ├── Task.kt
│   │           ├── Category.kt
│   │           └── Priority.kt
│   │
│   ├── database/                 # Room database layer
│   │   └── src/
│   │       ├── commonMain/kotlin/
│   │       │   └── de/inovex/kmp_training/core/database/
│   │       │       ├── TaskDatabase.kt
│   │       │       ├── DatabaseBuilder.kt
│   │       │       ├── entity/
│   │       │       └── dao/
│   │       ├── androidMain/      # Android database builder
│   │       └── iosMain/          # iOS database builder
│   │
│   ├── network/                  # Ktor networking layer
│   │   └── src/
│   │       ├── commonMain/kotlin/
│   │       │   └── de/inovex/kmp_training/core/network/
│   │       │       ├── HttpClientFactory.kt
│   │       │       ├── MockApiService.kt
│   │       │       └── dto/
│   │       ├── androidMain/      # CIO engine
│   │       └── iosMain/          # Darwin engine
│   │
│   └── data/                     # Repository layer
│       └── src/commonMain/kotlin/
│           └── de/inovex/kmp_training/core/data/repository/
│               ├── TaskRepository.kt
│               ├── TaskRepositoryImpl.kt
│               ├── CategoryRepository.kt
│               └── CategoryRepositoryImpl.kt
│
├── composeApp/                   # Main application
│   └── src/
│       ├── commonMain/kotlin/
│       │   └── de/inovex/kmp_training/
│       │       ├── App.kt
│       │       ├── di/           # Koin modules
│       │       └── ui/
│       │           ├── navigation/
│       │           ├── screens/
│       │           ├── components/
│       │           └── theme/
│       ├── androidMain/
│       └── iosMain/
│
└── iosApp/                       # iOS host app
```

## Features

### Task Management
- Create, edit, and delete tasks
- Mark tasks as completed
- Set due dates with date picker
- Assign priority levels (Low, Medium, High)
- Organize tasks by categories

### Categories
- Create custom categories with colors
- Filter tasks by category
- Edit and delete categories

### Search & Filtering
- Real-time search with debounce
- Filter by completion status
- Filter by priority level
- Filter by category

### Sorting
- Sort by creation date
- Sort by due date
- Sort by priority
- Sort alphabetically

### Remote Sync (Demo)
- Mock API service demonstrating Ktor usage
- Simulated network delays for realistic behavior
- Demonstrates sync patterns for offline-first apps

## Running the App

### Android
```bash
./gradlew :composeApp:assembleDebug
# Install on device/emulator
adb install composeApp/build/outputs/apk/debug/composeApp-debug.apk
```

### iOS
Open `iosApp/iosApp.xcodeproj` in Xcode and run the app on a simulator or device.

Or build the iOS framework:
```bash
./gradlew :composeApp:linkDebugFrameworkIosSimulatorArm64
```

## Key KMP Concepts Demonstrated

### 1. Room Database (Multiplatform)
- `@Database`, `@Entity`, `@Dao` annotations
- `@ConstructedBy` for non-Android platforms
- Platform-specific database builders using `expect`/`actual`
- Flow-based reactive queries

### 2. Koin Dependency Injection
- Shared modules across platforms
- Platform-specific initialization
- ViewModel injection with `koinViewModel()`

### 3. Ktor HTTP Client
- Platform-specific engines (CIO for Android, Darwin for iOS)
- Content negotiation with kotlinx.serialization
- Configurable logging

### 4. Compose Multiplatform
- Shared UI code across platforms
- Material 3 theming
- Animated navigation transitions
- Platform-specific safe area handling

### 5. expect/actual Pattern
- Database builders (`getDatabaseBuilder()`)
- HTTP client engines (`createPlatformHttpClient()`)
- Platform information

### 6. Modular Architecture
- Clean separation of concerns
- Each layer can be tested independently
- Incremental integration for learning

## Dependencies

All dependencies are managed in `gradle/libs.versions.toml` using the Gradle Version Catalog.

## License

This project is for educational and training purposes.
