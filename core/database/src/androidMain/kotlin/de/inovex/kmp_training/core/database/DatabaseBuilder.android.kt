package de.inovex.kmp_training.core.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

lateinit var applicationContext: Context

fun initializeAndroidContext(context: Context) {
    applicationContext = context.applicationContext
}

actual fun getDatabaseBuilder(): RoomDatabase.Builder<TaskDatabase> {
    val dbFile = applicationContext.getDatabasePath(TaskDatabase.DATABASE_NAME)
    return Room.databaseBuilder<TaskDatabase>(
        context = applicationContext,
        name = dbFile.absolutePath
    )
}

