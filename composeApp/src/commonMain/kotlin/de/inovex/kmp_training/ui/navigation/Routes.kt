package de.inovex.kmp_training.ui.navigation

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data object TaskList : Route
    
    @Serializable
    data class TaskDetail(val taskId: Long? = null) : Route
    
    @Serializable
    data object Categories : Route
}

