package de.inovex.kmp_training.core.model

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class Task(
    val id: Long = 0,
    val title: String,
    val description: String = "",
    val isCompleted: Boolean = false,
    val dueDate: Instant? = null,
    val priority: Priority = Priority.MEDIUM,
    val categoryId: Long? = null,
    val tagIds: List<Long> = emptyList(),
    val createdAt: Instant
)

