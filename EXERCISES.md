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
4. **If stuck**: Run `git show solution:<filepath>` to peek at the solution

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
Create a Room Entity for storing tags in the database.

### Requirements
1. Create a `data class TagEntity` with:
   - `id: Long` - Primary key with auto-generate
   - `name: String`
   - `colorHex: String`

2. Annotate with `@Entity(tableName = "tags")`

3. Add a `toDomain()` function to convert to the `Tag` model

4. Add a companion object with `fromDomain(tag: Tag)` function

### Hints
- Look at `TaskEntity.kt` and `CategoryEntity.kt` for reference
- Import `Tag` from `de.inovex.kmp_training.core.model.Tag`

---

## Exercise 3: Room Database - TagDao (25 min)

**File**: `core/database/src/commonMain/kotlin/de/inovex/kmp_training/core/database/dao/TagDao.kt`

### Task
Create a Data Access Object (DAO) for tag operations.

### Requirements
1. Create an interface `TagDao` annotated with `@Dao`

2. Add these functions:
   - `getAllTags(): Flow<List<TagEntity>>` - Get all tags sorted by name
   - `getTagById(id: Long): TagEntity?` - Get a single tag (suspend)
   - `insertTag(tag: TagEntity): Long` - Insert with REPLACE strategy (suspend)
   - `deleteTag(tag: TagEntity)` - Delete a tag (suspend)
   - `deleteTagById(id: Long)` - Delete by ID (suspend)

### Hints
- Use `@Query`, `@Insert`, `@Delete` annotations
- Look at `TaskDao.kt` for examples
- For queries returning Flow, use `fun`; for one-time operations use `suspend fun`

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
Create a Data Transfer Object for tag API communication.

### Requirements
1. Create a `data class TagDto` with:
   - `id: Long`
   - `name: String`
   - `colorHex: String`

2. Add `@Serializable` annotation

3. Add `toDomain()` and `fromDomain()` functions

4. Create a `TagListResponse` class:
   ```kotlin
   @Serializable
   data class TagListResponse(
       val tags: List<TagDto>,
       val total: Int
   )
   ```

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

## Exercise 7: Koin - TagRepository (15 min)

**Files**:
- `core/data/src/commonMain/kotlin/de/inovex/kmp_training/core/data/repository/TagRepository.kt`
- `core/data/src/commonMain/kotlin/de/inovex/kmp_training/core/data/repository/TagRepositoryImpl.kt`

### Task
Create a repository that combines database and network operations.

### Requirements
1. Create interface `TagRepository` with:
   - `getAllTags(): Flow<List<Tag>>`
   - `insertTag(tag: Tag): Long`
   - `deleteTag(tag: Tag)`
   - `fetchRemoteTags(): Result<List<Tag>>`

2. Create class `TagRepositoryImpl` that:
   - Takes `TagDao` and `MockApiService` as constructor parameters
   - Implements all interface methods

### Hints
- Convert between domain models and entities/DTOs
- Use `map { }` on flows to convert lists

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

