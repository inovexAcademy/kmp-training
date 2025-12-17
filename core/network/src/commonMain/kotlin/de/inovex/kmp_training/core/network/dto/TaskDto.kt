package de.inovex.kmp_training.core.network.dto

import de.inovex.kmp_training.core.model.Priority
import de.inovex.kmp_training.core.model.Task
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class TaskDto(
    val id: Long,
    val title: String,
    val description: String,
    val isCompleted: Boolean,
    val dueDate: Long?,
    val priority: Int,
    val categoryId: Long?,
    val createdAt: Long
) {
    fun toDomain(): Task = Task(
        id = id,
        title = title,
        description = description,
        isCompleted = isCompleted,
        dueDate = dueDate?.let { Instant.fromEpochMilliseconds(it) },
        priority = Priority.fromLevel(priority),
        categoryId = categoryId,
        createdAt = Instant.fromEpochMilliseconds(createdAt)
    )
    
    companion object {
        fun fromDomain(task: Task): TaskDto = TaskDto(
            id = task.id,
            title = task.title,
            description = task.description,
            isCompleted = task.isCompleted,
            dueDate = task.dueDate?.toEpochMilliseconds(),
            priority = task.priority.level,
            categoryId = task.categoryId,
            createdAt = task.createdAt.toEpochMilliseconds()
        )
    }
}

@Serializable
data class TaskListResponse(
    val tasks: List<TaskDto>,
    val total: Int
)

