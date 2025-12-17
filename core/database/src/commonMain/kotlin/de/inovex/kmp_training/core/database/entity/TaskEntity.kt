package de.inovex.kmp_training.core.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import de.inovex.kmp_training.core.model.Priority
import de.inovex.kmp_training.core.model.Task
import kotlinx.datetime.Instant

@Entity(
    tableName = "tasks",
    foreignKeys = [
        ForeignKey(
            entity = CategoryEntity::class,
            parentColumns = ["id"],
            childColumns = ["categoryId"],
            onDelete = ForeignKey.SET_NULL
        )
    ],
    indices = [Index("categoryId")]
)
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val description: String,
    val isCompleted: Boolean,
    val dueDate: Long?, // Stored as epoch milliseconds
    val priority: Int, // Stored as Priority level
    val categoryId: Long?,
    val createdAt: Long // Stored as epoch milliseconds
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
        fun fromDomain(task: Task): TaskEntity = TaskEntity(
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

