package de.inovex.kmp_training.core.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import de.inovex.kmp_training.core.database.dao.CategoryDao
import de.inovex.kmp_training.core.database.dao.TaskDao
import de.inovex.kmp_training.core.database.entity.CategoryEntity
import de.inovex.kmp_training.core.database.entity.TaskEntity

@Database(
    entities = [TaskEntity::class, CategoryEntity::class],
    version = 1,
    exportSchema = true
)
@ConstructedBy(TaskDatabaseConstructor::class)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
    abstract fun categoryDao(): CategoryDao
    
    companion object {
        const val DATABASE_NAME = "task_database.db"
    }
}

// Room will generate the actual implementation
@Suppress("NO_ACTUAL_FOR_EXPECT", "EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect object TaskDatabaseConstructor : RoomDatabaseConstructor<TaskDatabase> {
    override fun initialize(): TaskDatabase
}

