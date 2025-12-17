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

1. **In `DatabaseModule.kt`**: Add a provider for TagDao
   - Follow the pattern of existing DAO providers

2. **In `RepositoryModule.kt`**: Add a provider for TagRepository
   - Follow the pattern of existing repository providers
   - Don't forget to add the necessary imports

### Hints
- Look at how TaskDao and TaskRepository are wired
- Use `single { }` for singleton instances

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
1. Create a composable function `TagChip` that accepts:
   - `tag: Tag` - The tag to display
   - `isSelected: Boolean` - Whether the tag is selected
   - `onClick: (() -> Unit)?` - Optional click handler
   - `modifier: Modifier` - Standard modifier

2. The component should display:
   - A colored dot based on `tag.colorHex`
   - The tag name
   - A check icon when selected (optional)

3. Style with Material 3 and rounded corners

### Hints
- Look at `TaskCard.kt` for component patterns
- You'll need to parse the hex color string to a Color
- Consider using `Surface` or `FilterChip` as the base

---

## Exercise 10: Compose - Tag Selection in TaskDetailScreen (25 min)

**File**: `src/commonMain/kotlin/de/inovex/kmp_training/ui/screens/taskdetail/TaskDetailScreen.kt`

### Task
Add tag selection to the task creation/editing screen.

### Requirements

**Step 1: Update TaskDetailViewModel**
- Add TagRepository as a constructor parameter
- Add state for selected tag IDs
- Add state for available tags (from repository)
- Add a function to toggle tag selection

**Step 2: Update ViewModelModule**
- Update the TaskDetailViewModel provider to include TagRepository

**Step 3: Update TaskDetailScreen**
- Add a "Tags" section similar to the "Category" section
- Use `FlowRow` to display available tags
- Use your TagChip component
- Handle tag selection/deselection

### Hints
- Study how categories are handled in the ViewModel and Screen
- Use `FlowRow` from `androidx.compose.foundation.layout`
- The pattern is similar to category selection but allows multiple selections

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
