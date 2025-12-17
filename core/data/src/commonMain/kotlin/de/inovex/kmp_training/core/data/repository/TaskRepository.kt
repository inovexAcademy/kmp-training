package de.inovex.kmp_training.core.data.repository

import de.inovex.kmp_training.core.model.Priority
import de.inovex.kmp_training.core.model.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    fun getAllTasks(): Flow<List<Task>>
    fun getTasksByCategory(categoryId: Long): Flow<List<Task>>
    fun getTasksByCompletionStatus(isCompleted: Boolean): Flow<List<Task>>
    fun getTasksByPriority(priority: Priority): Flow<List<Task>>
    fun searchTasks(query: String): Flow<List<Task>>
    
    suspend fun getTaskById(id: Long): Task?
    suspend fun insertTask(task: Task): Long
    suspend fun updateTask(task: Task)
    suspend fun deleteTask(task: Task)
    suspend fun deleteTaskById(id: Long)
    suspend fun toggleTaskCompletion(id: Long, isCompleted: Boolean)
    
    // Remote sync methods (demonstrating Ktor usage)
    suspend fun syncWithRemote(): Result<List<Task>>
    suspend fun fetchRemoteTasks(): Result<List<Task>>
}

