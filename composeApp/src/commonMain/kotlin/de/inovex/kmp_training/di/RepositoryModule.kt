package de.inovex.kmp_training.di

import de.inovex.kmp_training.core.data.repository.CategoryRepository
import de.inovex.kmp_training.core.data.repository.CategoryRepositoryImpl
import de.inovex.kmp_training.core.data.repository.TagRepository
import de.inovex.kmp_training.core.data.repository.TagRepositoryImpl
import de.inovex.kmp_training.core.data.repository.TaskRepository
import de.inovex.kmp_training.core.data.repository.TaskRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<TaskRepository> { TaskRepositoryImpl(get(), get(), get()) }
    single<CategoryRepository> { CategoryRepositoryImpl(get()) }
    single<TagRepository> { TagRepositoryImpl(get(), get()) }
}
