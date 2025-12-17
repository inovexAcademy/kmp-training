# Module: core/network

Ktor networking exercises for KMP.

## Running Tests

```bash
./gradlew :core:network:allTests
```

**Before implementation**: Tests fail with `NotImplementedError`  
**After implementation**: All tests should pass

---

## Exercise 1: Ktor - TagDto (20 min)

**File**: `src/commonMain/kotlin/de/inovex/kmp_training/core/network/dto/TagDto.kt`

### Task

Complete the TagDto implementation by replacing the `TODO()` calls.

### Requirements

The data class structure and `TagListResponse` are provided. Implement:

1. `toDomain()` - Convert TagDto to Tag model
2. `fromDomain(tag: Tag)` - Convert Tag model to TagDto

### Hints

- Look at `TaskDto.kt` for reference
- The pattern is similar to what you did in TagEntity

### Verify

```bash
./gradlew :core:network:allTests
```

All `TagDtoTest` tests should pass.

---

## Exercise 2: Ktor - API Methods (25 min)

**File**: `src/commonMain/kotlin/de/inovex/kmp_training/core/network/MockApiService.kt`

### Task

Add tag-related API methods to the mock service.

### Requirements

1. Add a private mutable list to store mock tags (similar to `mockTasks`)

2. Implement these suspend functions:
   - `fetchTags(): TagListResponse` - Return all tags
   - `fetchTagById(id: Long): TagDto?` - Find a tag by ID
   - `createTag(tag: TagDto): TagDto` - Add a new tag
   - `deleteTag(id: Long): Boolean` - Remove a tag

### Hints

- Study the existing task methods for the pattern
- Don't forget to call `simulateNetworkCall()`
- Use `Tag.PREDEFINED_TAGS` for initial mock data inspiration

---

## Next Steps

Once all tests pass, continue to the next module:

```bash
open ../data/EXERCISE.md
```
