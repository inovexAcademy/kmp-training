# KMP Training Exercises

This training is designed to teach Kotlin Multiplatform development through hands-on exercises. You will implement a **Tag** feature across all layers of the application.

## Prerequisites

- Clone the repository and open it in Android Studio or IntelliJ IDEA
- You are on the `main` branch (exercises with TODOs)
- The `solution` branch contains the complete implementation if you need help

## How to Use This Training

1. **Follow the exercises in order** - Each exercise builds on the previous one
2. **Look for TODO comments** - Search for `TODO: Exercise` in the codebase
3. **Run tests** to verify your implementation is correct
4. **If stuck**: Run `git show solution:<filepath>` to peek at the solution

## Exercise Modules

Each module has its own `EXERCISE.md` file. Complete them in order:

| Order | Module | Exercise File | Topics |
|-------|--------|---------------|--------|
| 1 | `core/database` | [EXERCISE.md](core/database/EXERCISE.md) | Room Entity, DAO, expect/actual |
| 2 | `core/network` | [EXERCISE.md](core/network/EXERCISE.md) | Ktor, DTOs, Serialization |
| 3 | `core/data` | [EXERCISE.md](core/data/EXERCISE.md) | Repository pattern |
| 4 | `composeApp` | [EXERCISE.md](composeApp/EXERCISE.md) | Koin DI, Compose UI |

## Getting Started

Start with Exercise 1 in `core/database/EXERCISE.md`:

```bash
# Open the first exercise
open core/database/EXERCISE.md
```

## Need Help?

- Compare with solution: `git diff main solution -- <filepath>`
- View solution file: `git show solution:<filepath>`
- Reset your work: `git checkout -- <filepath>`
- Reset everything: `git checkout .`
