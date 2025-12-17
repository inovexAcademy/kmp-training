package de.inovex.kmp_training.core.database

import androidx.room.RoomDatabase

expect fun getDatabaseBuilder(): RoomDatabase.Builder<TaskDatabase>

