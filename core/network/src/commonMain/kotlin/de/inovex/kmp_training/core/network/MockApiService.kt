package de.inovex.kmp_training.core.network

import de.inovex.kmp_training.core.model.Priority
import de.inovex.kmp_training.core.network.dto.TaskDto
import de.inovex.kmp_training.core.network.dto.TaskListResponse
import io.ktor.client.HttpClient
import kotlinx.coroutines.delay
import kotlinx.datetime.Clock

/**
 * Mock API Service demonstrating how Ktor client would be used
 * in a real application. This simulates network calls with delays
 * and returns mock data.
 * 
 * In a real-world scenario, you would use:
 * - client.get("https://api.example.com/tasks")
 * - client.post("https://api.example.com/tasks") { setBody(taskDto) }
 * - etc.
 */
class MockApiService(
    @Suppress("unused") private val client: HttpClient
) {
    
    // Simulated network delay
    private suspend fun simulateNetworkCall() {
        delay(500L)
    }
    
    // Mock data store (in-memory)
    private val mockTasks = mutableListOf(
        createMockTask(1, "Learn Kotlin Multiplatform", "Study KMP fundamentals and architecture", Priority.HIGH),
        createMockTask(2, "Set up Room Database", "Configure Room for local persistence", Priority.HIGH),
        createMockTask(3, "Implement Koin DI", "Add dependency injection with Koin", Priority.MEDIUM),
        createMockTask(4, "Create UI with Compose", "Build the user interface", Priority.MEDIUM),
        createMockTask(5, "Add Ktor networking", "Demonstrate API calls with Ktor", Priority.LOW)
    )
    
    /**
     * Simulates fetching tasks from a remote API
     */
    suspend fun fetchTasks(): TaskListResponse {
        simulateNetworkCall()
        return TaskListResponse(
            tasks = mockTasks.toList(),
            total = mockTasks.size
        )
    }
    
    /**
     * Simulates fetching a single task by ID
     */
    suspend fun fetchTaskById(id: Long): TaskDto? {
        simulateNetworkCall()
        return mockTasks.find { it.id == id }
    }
    
    /**
     * Simulates creating a new task via API
     */
    suspend fun createTask(task: TaskDto): TaskDto {
        simulateNetworkCall()
        val newTask = task.copy(id = (mockTasks.maxOfOrNull { it.id } ?: 0) + 1)
        mockTasks.add(newTask)
        return newTask
    }
    
    /**
     * Simulates updating a task via API
     */
    suspend fun updateTask(task: TaskDto): TaskDto {
        simulateNetworkCall()
        val index = mockTasks.indexOfFirst { it.id == task.id }
        if (index != -1) {
            mockTasks[index] = task
        }
        return task
    }
    
    /**
     * Simulates deleting a task via API
     */
    suspend fun deleteTask(id: Long): Boolean {
        simulateNetworkCall()
        return mockTasks.removeAll { it.id == id }
    }
    
    /**
     * Simulates syncing local data with remote
     */
    suspend fun syncTasks(localTasks: List<TaskDto>): TaskListResponse {
        simulateNetworkCall()
        // In a real app, this would merge local and remote data
        // For demo purposes, we just return what was sent
        return TaskListResponse(
            tasks = localTasks,
            total = localTasks.size
        )
    }
    
    private fun createMockTask(
        id: Long,
        title: String,
        description: String,
        priority: Priority
    ): TaskDto {
        val now = Clock.System.now().toEpochMilliseconds()
        return TaskDto(
            id = id,
            title = title,
            description = description,
            isCompleted = false,
            dueDate = now + (id * 24 * 60 * 60 * 1000), // Due in 'id' days
            priority = priority.level,
            categoryId = null,
            createdAt = now
        )
    }
}

