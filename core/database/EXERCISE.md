# Module: core/database

Room Database exercises for KMP.

## Running Tests

```bash
./gradlew :core:database:allTests
```

**Before implementation**: Tests fail with `NotImplementedError`  
**After implementation**: All tests should pass

---

## Exercise 1: Explore the Project Structure (15 min)

No coding required - just explore and understand the codebase.

### Task
Familiarize yourself with the `expect`/`actual` pattern by exploring:

- `src/commonMain/kotlin/.../DatabaseBuilder.kt` (expect declaration)
- `src/androidMain/kotlin/.../DatabaseBuilder.android.kt` (Android actual)
- `src/iosMain/kotlin/.../DatabaseBuilder.ios.kt` (iOS actual)

### Discussion Points
- Why is `expect`/`actual` needed for the database builder?
- What are alternatives to this pattern?
- When would you use interfaces instead?

---

## Exercise 2: Room Database - TagEntity (20 min)

**File**: `src/commonMain/kotlin/de/inovex/kmp_training/core/database/entity/TagEntity.kt`

### Task
Complete the TagEntity implementation by replacing the `TODO()` calls.

### Requirements
The data class structure is provided. Implement:

1. `toDomain()` - Convert TagEntity to Tag model
2. `fromDomain(tag: Tag)` - Convert Tag model to TagEntity

### Hints
- Look at `TaskEntity.kt` and `CategoryEntity.kt` for reference

### Verify
```bash
./gradlew :core:database:allTests
```
All `TagEntityTest` tests should pass.

---

## Exercise 3: Room Database - TagDao (15 min)

**File**: `src/commonMain/kotlin/de/inovex/kmp_training/core/database/dao/TagDao.kt`

### Task
Study the provided TagDao interface to understand Room DAO patterns.

### Provided Implementation
The TagDao is already implemented. Review it to understand:

1. `@Dao` annotation marks this as a Room DAO
2. `@Query` executes SQL queries
3. `@Insert` with conflict strategy handles duplicates
4. `@Delete` removes entities
5. `Flow<List<T>>` provides reactive updates
6. `suspend fun` is used for one-time operations

### Discussion Points
- Why does `getAllTags()` return `Flow` but `getTagById()` uses `suspend`?
- What does the conflict strategy do?
- How does Room generate the implementation?

---

## Exercise 4: Room Database - Register in Database (Bonus)

**File**: `src/commonMain/kotlin/de/inovex/kmp_training/core/database/TaskDatabase.kt`

### Task
Register the new entity and DAO in the database.

### Requirements
1. Add the TagEntity to the entities array in `@Database`
2. Add an abstract function to expose the TagDao
3. Increment the database version

### Note
After changing the database version, you may need to uninstall the app to clear old data.

---

## Next Steps

Once all tests pass, continue to the next module:

```bash
open ../network/EXERCISE.md
```
