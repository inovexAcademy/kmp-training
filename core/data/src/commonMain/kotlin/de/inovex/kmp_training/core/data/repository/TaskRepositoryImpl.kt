package de.inovex.kmp_training.core.data.repository

import de.inovex.kmp_training.core.database.dao.TagDao
import de.inovex.kmp_training.core.database.dao.TaskDao
import de.inovex.kmp_training.core.database.entity.TaskEntity
import de.inovex.kmp_training.core.database.entity.TaskTagCrossRef
import de.inovex.kmp_training.core.model.Priority
import de.inovex.kmp_training.core.model.Task
import de.inovex.kmp_training.core.network.MockApiService
import de.inovex.kmp_training.core.network.dto.TaskDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TaskRepositoryImpl(
    private val taskDao: TaskDao,
    private val tagDao: TagDao,
    private val mockApiService: MockApiService
) : TaskRepository {
    
    override fun getAllTasks(): Flow<List<Task>> {
        return taskDao.getAllTasks().map { entities ->
            entities.map { entity ->
                val tagIds = tagDao.getTagIdsForTask(entity.id)
                entity.toDomain(tagIds)
            }
        }
    }
    
    override fun getTasksByCategory(categoryId: Long): Flow<List<Task>> {
        return taskDao.getTasksByCategory(categoryId).map { entities ->
            entities.map { entity ->
                val tagIds = tagDao.getTagIdsForTask(entity.id)
                entity.toDomain(tagIds)
            }
        }
    }
    
    override fun getTasksByCompletionStatus(isCompleted: Boolean): Flow<List<Task>> {
        return taskDao.getTasksByCompletionStatus(isCompleted).map { entities ->
            entities.map { entity ->
                val tagIds = tagDao.getTagIdsForTask(entity.id)
                entity.toDomain(tagIds)
            }
        }
    }
    
    override fun getTasksByPriority(priority: Priority): Flow<List<Task>> {
        return taskDao.getTasksByPriority(priority.level).map { entities ->
            entities.map { entity ->
                val tagIds = tagDao.getTagIdsForTask(entity.id)
                entity.toDomain(tagIds)
            }
        }
    }
    
    override fun searchTasks(query: String): Flow<List<Task>> {
        return taskDao.searchTasks(query).map { entities ->
            entities.map { entity ->
                val tagIds = tagDao.getTagIdsForTask(entity.id)
                entity.toDomain(tagIds)
            }
        }
    }
    
    override suspend fun getTaskById(id: Long): Task? {
        val entity = taskDao.getTaskById(id) ?: return null
        val tagIds = tagDao.getTagIdsForTask(id)
        return entity.toDomain(tagIds)
    }
    
    override suspend fun insertTask(task: Task): Long {
        val taskId = taskDao.insertTask(TaskEntity.fromDomain(task))
        // Save tag associations
        if (task.tagIds.isNotEmpty()) {
            val crossRefs = task.tagIds.map { tagId ->
                TaskTagCrossRef(taskId = taskId, tagId = tagId)
            }
            tagDao.insertTaskTagCrossRefs(crossRefs)
        }
        return taskId
    }
    
    override suspend fun updateTask(task: Task) {
        taskDao.updateTask(TaskEntity.fromDomain(task))
        // Update tag associations
        tagDao.deleteTaskTagCrossRefsByTaskId(task.id)
        if (task.tagIds.isNotEmpty()) {
            val crossRefs = task.tagIds.map { tagId ->
                TaskTagCrossRef(taskId = task.id, tagId = tagId)
            }
            tagDao.insertTaskTagCrossRefs(crossRefs)
        }
    }
    
    override suspend fun deleteTask(task: Task) {
        taskDao.deleteTask(TaskEntity.fromDomain(task))
    }
    
    override suspend fun deleteTaskById(id: Long) {
        taskDao.deleteTaskById(id)
    }
    
    override suspend fun toggleTaskCompletion(id: Long, isCompleted: Boolean) {
        taskDao.updateTaskCompletion(id, isCompleted)
    }
    
    override suspend fun syncWithRemote(): Result<List<Task>> {
        return try {
            // Get local tasks
            val localTasks = mutableListOf<TaskDto>()
            // In a real app, you would collect the flow here
            
            // Sync with remote (mock API)
            val response = mockApiService.syncTasks(localTasks)
            
            // Save remote tasks to local database
            response.tasks.forEach { taskDto ->
                taskDao.insertTask(TaskEntity.fromDomain(taskDto.toDomain()))
            }
            
            Result.success(response.tasks.map { it.toDomain() })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    override suspend fun fetchRemoteTasks(): Result<List<Task>> {
        return try {
            val response = mockApiService.fetchTasks()
            
            // Optionally save to local database
            response.tasks.forEach { taskDto ->
                taskDao.insertTask(TaskEntity.fromDomain(taskDto.toDomain()))
            }
            
            Result.success(response.tasks.map { it.toDomain() })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

