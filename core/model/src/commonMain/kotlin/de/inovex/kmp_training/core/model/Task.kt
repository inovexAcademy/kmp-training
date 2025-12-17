package de.inovex.kmp_training.core.model

import kotlinx.datetime.Instant

data class Task(
    val id: Long = 0,
    val title: String,
    val description: String = "",
    val isCompleted: Boolean = false,
    val dueDate: Instant? = null,
    val priority: Priority = Priority.MEDIUM,
    val categoryId: Long? = null,
    val createdAt: Instant
)

