# KMP Training Exercises

This training is designed to teach Kotlin Multiplatform development through hands-on exercises. You will implement a **Tag** feature across all layers of the application.

## Prerequisites

- Clone the repository and open it in Android Studio or IntelliJ IDEA
- You are on the `main` branch (exercises with TODOs)
- The `solution` branch contains the complete implementation if you need help

## How to Use This Training

1. **Follow the exercises in order** - Each exercise builds on the previous one
2. **Look for TODO comments** - Search for `TODO: Exercise` in the codebase
3. **Run the app** after each exercise to verify your implementation
4. **Run tests** to verify your implementation is correct (see below)
5. **If stuck**: Run `git show solution:<filepath>` to peek at the solution

## Verifying Your Implementation with Tests

Unit tests are included on the `main` branch. Run them to verify your implementation:

```bash
# Run all exercise tests
./gradlew :core:database:allTests :core:network:allTests :core:data:allTests

# Or run tests for a specific module
./gradlew :core:database:allTests    # Exercise 2: TagEntity
./gradlew :core:network:allTests     # Exercise 5: TagDto
./gradlew :core:data:allTests        # Exercise 7: TagRepository
```

**Before implementation**: Tests will fail with `NotImplementedError`
**After implementation**: Tests should pass

| Exercise | Test Class | What It Tests |
|----------|------------|---------------|
| Exercise 2 | `TagEntityTest` | `toDomain()` and `fromDomain()` conversions |
| Exercise 5 | `TagDtoTest` | DTO conversions and JSON serialization |
| Exercise 7 | `TagRepositoryTest` | Repository interface and Tag model |

---

## Exercise 1: Explore the Project Structure (15 min)

No coding required - just explore and understand the codebase.

### Task
Familiarize yourself with the `expect`/`actual` pattern by exploring:

- `core/database/src/commonMain/kotlin/.../DatabaseBuilder.kt` (expect declaration)
- `core/database/src/androidMain/kotlin/.../DatabaseBuilder.android.kt` (Android actual)
- `core/database/src/iosMain/kotlin/.../DatabaseBuilder.ios.kt` (iOS actual)

### Discussion Points
- Why is `expect`/`actual` needed for the database builder?
- What are alternatives to this pattern?
- When would you use interfaces instead?

---

## Exercise 2: Room Database - TagEntity (20 min)

**File**: `core/database/src/commonMain/kotlin/de/inovex/kmp_training/core/database/entity/TagEntity.kt`

### Task
Complete the TagEntity implementation by replacing the `TODO()` calls.

### Requirements
The data class structure is provided. Implement:

1. `toDomain()` - Convert TagEntity to Tag model
2. `fromDomain(tag: Tag)` - Convert Tag model to TagEntity

### Hints
- Look at `TaskEntity.kt` and `CategoryEntity.kt` for reference
- Map each field directly (id → id, name → name, colorHex → colorHex)

### Verify
```bash
./gradlew :core:database:allTests
```
All `TagEntityTest` tests should pass.

---

## Exercise 3: Room Database - TagDao (15 min)

**File**: `core/database/src/commonMain/kotlin/de/inovex/kmp_training/core/database/dao/TagDao.kt`

### Task
Study the provided TagDao interface to understand Room DAO patterns.

### Provided Implementation
The TagDao is already implemented. Review it to understand:

1. `@Dao` annotation marks this as a Room DAO
2. `@Query` executes SQL queries
3. `@Insert(onConflict = OnConflictStrategy.REPLACE)` handles conflicts
4. `@Delete` removes entities
5. `Flow<List<T>>` provides reactive updates (no `suspend` needed)
6. `suspend fun` is used for one-time operations

### Discussion Points
- Why does `getAllTags()` return `Flow` but `getTagById()` uses `suspend`?
- What does `OnConflictStrategy.REPLACE` do?
- How does Room generate the implementation?

---

## Exercise 4: Room Database - Register in Database (Bonus)

**File**: `core/database/src/commonMain/kotlin/de/inovex/kmp_training/core/database/TaskDatabase.kt`

### Task
Register the new entity and DAO in the database.

### Requirements
1. Add `TagEntity::class` to the `entities` array in `@Database`
2. Add `abstract fun tagDao(): TagDao`
3. Increment the database version (e.g., from 1 to 2)

### Note
After changing the database version, you may need to uninstall the app to clear old data.

---

## Exercise 5: Ktor - TagDto (20 min)

**File**: `core/network/src/commonMain/kotlin/de/inovex/kmp_training/core/network/dto/TagDto.kt`

### Task
Complete the TagDto implementation by replacing the `TODO()` calls.

### Requirements
The data class structure and `TagListResponse` are provided. Implement:

1. `toDomain()` - Convert TagDto to Tag model
2. `fromDomain(tag: Tag)` - Convert Tag model to TagDto

### Hints
- The structure is similar to TagEntity
- Map each field directly (id → id, name → name, colorHex → colorHex)

### Verify
```bash
./gradlew :core:network:allTests
```
All `TagDtoTest` tests should pass.

---

## Exercise 6: Ktor - API Methods (25 min)

**File**: `core/network/src/commonMain/kotlin/de/inovex/kmp_training/core/network/MockApiService.kt`

### Task
Add tag-related API methods to the mock service.

### Requirements
1. Add a mock tags list (like `mockTasks`):
   ```kotlin
   private val mockTags = mutableListOf(
       TagDto(id = 1, name = "Work", colorHex = "#4A90D9"),
       // Add more tags...
   )
   ```

2. Implement these suspend functions:
   - `fetchTags(): TagListResponse`
   - `fetchTagById(id: Long): TagDto?`
   - `createTag(tag: TagDto): TagDto`
   - `deleteTag(id: Long): Boolean`

### Hints
- Follow the pattern of existing task methods
- Use `simulateNetworkCall()` to simulate network delay

---

## Exercise 7: Koin - TagRepository (20 min)

**File**: `core/data/src/commonMain/kotlin/de/inovex/kmp_training/core/data/repository/TagRepositoryImpl.kt`

### Task
Complete the TagRepositoryImpl by replacing the `TODO()` calls.

### Provided
- `TagRepository` interface is complete
- `TagRepositoryImpl` class structure is provided

### Requirements
Implement these methods in `TagRepositoryImpl`:

1. `getAllTags()` - Get all tags from database
   - Use `tagDao.getAllTags()` and map entities to domain models

2. `insertTag(tag: Tag)` - Insert a tag
   - Convert Tag to TagEntity using `TagEntity.fromDomain(tag)`
   - Call `tagDao.insertTag(entity)`

3. `deleteTag(tag: Tag)` - Delete a tag
   - Convert and call `tagDao.deleteTag(entity)`

4. `fetchRemoteTags()` - Fetch from API
   - Call `mockApiService.fetchTags()`
   - Use try/catch to return `Result.success()` or `Result.failure()`

### Hints
- Use `.map { entities -> entities.map { it.toDomain() } }` on flows
- Look at `TaskRepositoryImpl.kt` for reference

### Verify
```bash
./gradlew :core:data:allTests
```
All `TagRepositoryTest` tests should pass.

---

## Exercise 8: Koin - DI Wiring (15 min)

**Files**:
- `composeApp/src/commonMain/kotlin/de/inovex/kmp_training/di/DatabaseModule.kt`
- `composeApp/src/commonMain/kotlin/de/inovex/kmp_training/di/RepositoryModule.kt`

### Task
Wire up the new dependencies in Koin modules.

### Requirements
1. In `DatabaseModule`:
   - Add `single { get<TaskDatabase>().tagDao() }`

2. In `RepositoryModule`:
   - Add `single<TagRepository> { TagRepositoryImpl(get(), get()) }`

### Verify
After this exercise, the app should compile without errors (though tags won't be visible in UI yet).

---

## Exercise 9: Compose - TagChip Component (20 min)

**File**: `composeApp/src/commonMain/kotlin/de/inovex/kmp_training/ui/components/TagChip.kt`

### Task
Create a reusable Compose component for displaying tags.

### Requirements
1. Create a `@Composable fun TagChip`:
   ```kotlin
   @Composable
   fun TagChip(
       tag: Tag,
       isSelected: Boolean = false,
       onClick: (() -> Unit)? = null,
       modifier: Modifier = Modifier
   )
   ```

2. Display:
   - Colored dot based on `tag.colorHex`
   - Tag name
   - Check icon when selected (optional)

3. Use Material 3 styling with rounded corners

### Hints
- Look at `CategoryChip` in `TaskCard.kt` for inspiration
- Parse hex color using a helper function
- Use `FilterChip` or custom Row with background

---

## Exercise 10: Compose - Tag Selection in TaskDetailScreen (25 min)

**File**: `composeApp/src/commonMain/kotlin/de/inovex/kmp_training/ui/screens/taskdetail/TaskDetailScreen.kt`

### Task
Add tag selection to the task creation/editing screen.

### Requirements
1. Add a "Tags" section (similar to "Category" section)
2. Use `FlowRow` to display available tags
3. Allow multiple tag selection
4. Show selected state with `TagChip`

### Steps
1. First, update `TaskDetailViewModel` to:
   - Include `TagRepository` dependency
   - Add `selectedTagIds` state
   - Add `availableTags` from repository
   - Add `onTagToggle(tagId: Long)` function

2. Then update `TaskDetailScreen` to:
   - Display tags in a card section
   - Handle tag selection/deselection

---

## Bonus Exercises

### Bonus A: Display Tags on TaskCard
Show tags on each task in the list view.

### Bonus B: Filter by Tag
Add a tag filter to the task list screen.

### Bonus C: Tag Management Screen
Create a dedicated screen for managing tags (add/edit/delete).

---

## Verification

After completing all exercises:

1. Create a new task with tags selected
2. Verify tags are saved and displayed
3. Edit the task and change tags
4. Verify the changes persist

## Need Help?

- Compare with solution: `git diff main solution -- <filepath>`
- View solution file: `git show solution:<filepath>`
- Reset your work: `git checkout -- <filepath>`
- Reset everything: `git checkout .`

Happy coding! 🚀

