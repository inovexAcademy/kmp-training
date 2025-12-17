# Module: composeApp

Koin dependency injection and Compose UI exercises.

---

## Exercise 8: Koin - DI Wiring (15 min)

**Files**:
- `src/commonMain/kotlin/de/inovex/kmp_training/di/DatabaseModule.kt`
- `src/commonMain/kotlin/de/inovex/kmp_training/di/RepositoryModule.kt`

### Task
Wire up the new Tag dependencies in Koin modules.

### Requirements

#### In `DatabaseModule.kt`:
Add a provider for TagDao:

```kotlin
single { get<TaskDatabase>().tagDao() }
```

#### In `RepositoryModule.kt`:
Add a provider for TagRepository:

```kotlin
single<TagRepository> { TagRepositoryImpl(get(), get()) }
```

Don't forget to add the imports:
```kotlin
import de.inovex.kmp_training.core.data.repository.TagRepository
import de.inovex.kmp_training.core.data.repository.TagRepositoryImpl
```

### Verify
After this exercise, the app should compile without errors:
```bash
./gradlew assembleDebug
```

---

## Exercise 9: Compose - TagChip Component (20 min)

**File**: `src/commonMain/kotlin/de/inovex/kmp_training/ui/components/TagChip.kt`

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

### Example Implementation
```kotlin
@Composable
fun TagChip(
    tag: Tag,
    isSelected: Boolean = false,
    onClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    val backgroundColor = if (isSelected) {
        MaterialTheme.colorScheme.primaryContainer
    } else {
        MaterialTheme.colorScheme.surfaceVariant
    }
    
    Surface(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .then(if (onClick != null) Modifier.clickable { onClick() } else Modifier),
        color = backgroundColor
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            // Colored dot
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .background(
                        color = parseHexColor(tag.colorHex),
                        shape = CircleShape
                    )
            )
            
            Text(
                text = tag.name,
                style = MaterialTheme.typography.labelMedium
            )
            
            if (isSelected) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Selected",
                    modifier = Modifier.size(14.dp)
                )
            }
        }
    }
}

private fun parseHexColor(hex: String): Color {
    return try {
        Color(hex.removePrefix("#").toLong(16) or 0xFF000000)
    } catch (e: Exception) {
        Color.Gray
    }
}
```

### Hints
- Look at `TaskCard.kt` for similar component patterns
- Use `Surface` with `RoundedCornerShape` for the chip background
- Handle hex color parsing with a try/catch

---

## Exercise 10: Compose - Tag Selection in TaskDetailScreen (25 min)

**File**: `src/commonMain/kotlin/de/inovex/kmp_training/ui/screens/taskdetail/TaskDetailScreen.kt`

### Task
Add tag selection to the task creation/editing screen.

### Step 1: Update TaskDetailViewModel

Add to `TaskDetailViewModel.kt`:

```kotlin
// Add TagRepository to constructor
class TaskDetailViewModel(
    private val taskRepository: TaskRepository,
    private val categoryRepository: CategoryRepository,
    private val tagRepository: TagRepository,  // Add this
    private val taskId: Long?
) : ViewModel() {

    // Add state for tags
    private val _selectedTagIds = MutableStateFlow<List<Long>>(emptyList())
    
    // Add to UI state
    data class TaskDetailUiState(
        // ... existing fields ...
        val selectedTagIds: List<Long> = emptyList(),
        val availableTags: List<Tag> = emptyList()
    )
    
    // Add toggle function
    fun onTagToggle(tagId: Long) {
        val currentTagIds = _selectedTagIds.value.toMutableList()
        if (currentTagIds.contains(tagId)) {
            currentTagIds.remove(tagId)
        } else {
            currentTagIds.add(tagId)
        }
        _selectedTagIds.value = currentTagIds
    }
}
```

### Step 2: Update ViewModelModule

In `di/ViewModelModule.kt`, update the TaskDetailViewModel provider:

```kotlin
viewModel { (taskId: Long?) -> 
    TaskDetailViewModel(get(), get(), get(), taskId) 
}
```

### Step 3: Add Tags Section to TaskDetailScreen

Add after the Category section:

```kotlin
// Tags selector
if (uiState.availableTags.isNotEmpty()) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Tags",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(12.dp))
            
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                uiState.availableTags.forEach { tag ->
                    TagChip(
                        tag = tag,
                        isSelected = uiState.selectedTagIds.contains(tag.id),
                        onClick = { viewModel.onTagToggle(tag.id) }
                    )
                }
            }
        }
    }
}
```

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

1. Build the app: `./gradlew assembleDebug`
2. Run the app on Android emulator
3. Create a new task with tags selected
4. Verify tags are saved and displayed
5. Edit the task and change tags
6. Verify the changes persist

---

## Congratulations!

You have completed the KMP Training exercises. You've learned:

- Room database with multiplatform support
- Ktor for networking with mock APIs
- Koin for dependency injection
- Compose Multiplatform for UI
- The repository pattern for data management
- expect/actual for platform-specific code

