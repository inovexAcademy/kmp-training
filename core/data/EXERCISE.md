# Module: core/data

Repository pattern exercises for KMP.

## Running Tests

```bash
./gradlew :core:data:allTests
```

---

## Exercise 7: Repository - TagRepositoryImpl (20 min)

**File**: `src/commonMain/kotlin/de/inovex/kmp_training/core/data/repository/TagRepositoryImpl.kt`

### Task
Complete the TagRepositoryImpl by replacing the `TODO()` calls.

### Provided
- `TagRepository` interface is complete
- `TagRepositoryImpl` class structure is provided

### Requirements
Implement these methods:

1. **`getAllTags()`** - Get all tags from the database as a Flow
   - Use the DAO to get entities
   - Transform the Flow to convert entities to domain models

2. **`insertTag(tag: Tag)`** - Insert a tag into the database
   - Convert the domain model to an entity
   - Use the DAO to insert

3. **`deleteTag(tag: Tag)`** - Delete a tag from the database
   - Convert the domain model to an entity
   - Use the DAO to delete

4. **`fetchRemoteTags()`** - Fetch tags from the API
   - Call the mock API service
   - Return `Result.success()` with the tags, or `Result.failure()` on error

### Hints
- Look at `TaskRepositoryImpl.kt` for the pattern
- Use `.map { }` to transform Flow contents
- Wrap API calls in try/catch for error handling

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
