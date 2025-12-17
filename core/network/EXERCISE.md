# Module: core/network

Ktor networking exercises for KMP.

## Running Tests

```bash
./gradlew :core:network:allTests
```

**Before implementation**: Tests fail with `NotImplementedError`  
**After implementation**: All tests should pass

---

## Exercise 5: Ktor - TagDto (20 min)

**File**: `src/commonMain/kotlin/de/inovex/kmp_training/core/network/dto/TagDto.kt`

### Task
Complete the TagDto implementation by replacing the `TODO()` calls.

### Requirements
The data class structure and `TagListResponse` are provided. Implement:

1. `toDomain()` - Convert TagDto to Tag model
2. `fromDomain(tag: Tag)` - Convert Tag model to TagDto

### Hints
- The structure is similar to TagEntity
- Map each field directly (id -> id, name -> name, colorHex -> colorHex)

### Verify
```bash
./gradlew :core:network:allTests
```
All `TagDtoTest` tests should pass.

---

## Exercise 6: Ktor - API Methods (25 min)

**File**: `src/commonMain/kotlin/de/inovex/kmp_training/core/network/MockApiService.kt`

### Task
Add tag-related API methods to the mock service.

### Requirements
1. Add a mock tags list (like `mockTasks`):
   ```kotlin
   private val mockTags = mutableListOf(
       TagDto(id = 1, name = "Work", colorHex = "#4A90D9"),
       TagDto(id = 2, name = "Personal", colorHex = "#50C878"),
       TagDto(id = 3, name = "Urgent", colorHex = "#FF6B6B"),
       TagDto(id = 4, name = "Home", colorHex = "#FFB347"),
       TagDto(id = 5, name = "Shopping", colorHex = "#DDA0DD")
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

### Example Implementation
```kotlin
suspend fun fetchTags(): TagListResponse {
    simulateNetworkCall()
    return TagListResponse(
        tags = mockTags.toList(),
        total = mockTags.size
    )
}
```

---

## Next Steps

Once all tests pass, continue to the next module:

```bash
open ../data/EXERCISE.md
```

