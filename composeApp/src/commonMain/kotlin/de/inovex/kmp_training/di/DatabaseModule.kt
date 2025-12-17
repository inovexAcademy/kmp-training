package de.inovex.kmp_training.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import de.inovex.kmp_training.core.database.TaskDatabase
import de.inovex.kmp_training.core.database.getDatabaseBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.dsl.module

val databaseModule = module {
    single<TaskDatabase> {
        getDatabaseBuilder()
            .setDriver(BundledSQLiteDriver())
            .setQueryCoroutineContext(Dispatchers.IO)
            .build()
    }
    
    single { get<TaskDatabase>().taskDao() }
    single { get<TaskDatabase>().categoryDao() }
}
