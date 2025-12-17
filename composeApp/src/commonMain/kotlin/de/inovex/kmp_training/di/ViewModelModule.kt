package de.inovex.kmp_training.di

import de.inovex.kmp_training.ui.screens.categories.CategoriesViewModel
import de.inovex.kmp_training.ui.screens.taskdetail.TaskDetailViewModel
import de.inovex.kmp_training.ui.screens.tasklist.TaskListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::TaskListViewModel)
    viewModel { (taskId: Long?) -> TaskDetailViewModel(get(), get(), taskId) }
    viewModelOf(::CategoriesViewModel)
}
