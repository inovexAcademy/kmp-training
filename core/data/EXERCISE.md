# Module: core/data

Repository pattern exercises for KMP.

## Running Tests

```bash
./gradlew :core:data:allTests
```

---

## Exercise 7: Koin - TagRepository (20 min)

**File**: `src/commonMain/kotlin/de/inovex/kmp_training/core/data/repository/TagRepositoryImpl.kt`

### Task
Complete the TagRepositoryImpl by replacing the `TODO()` calls.

### Provided
- `TagRepository` interface is complete
- `TagRepositoryImpl` class structure is provided

### Requirements
Implement these methods in `TagRepositoryImpl`:

### 1. `getAllTags()`
Get all tags from the database as a Flow.

```kotlin
override fun getAllTags(): Flow<List<Tag>> {
    return tagDao.getAllTags().map { entities ->
        entities.map { it.toDomain() }
    }
}
```

### 2. `insertTag(tag: Tag)`
Insert a tag into the database.

```kotlin
override suspend fun insertTag(tag: Tag): Long {
    return tagDao.insertTag(TagEntity.fromDomain(tag))
}
```

### 3. `deleteTag(tag: Tag)`
Delete a tag from the database.

```kotlin
override suspend fun deleteTag(tag: Tag) {
    tagDao.deleteTag(TagEntity.fromDomain(tag))
}
```

### 4. `fetchRemoteTags()`
Fetch tags from the API and return a Result.

```kotlin
override suspend fun fetchRemoteTags(): Result<List<Tag>> {
    return try {
        val response = mockApiService.fetchTags()
        Result.success(response.tags.map { it.toDomain() })
    } catch (e: Exception) {
        Result.failure(e)
    }
}
```

### Hints
- Import `TagEntity` from the database module
- Use `.map { }` to transform Flow contents
- Look at `TaskRepositoryImpl.kt` for reference

### Verify
```bash
./gradlew :core:data:allTests
```
All `TagRepositoryTest` tests should pass.

---

## Next Steps

Once complete, continue to the composeApp module:

```bash
open ../../composeApp/EXERCISE.md
```

