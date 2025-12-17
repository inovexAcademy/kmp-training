package de.inovex.kmp_training.core.data.repository

import de.inovex.kmp_training.core.model.Tag
import kotlinx.coroutines.flow.Flow

interface TagRepository {
    fun getAllTags(): Flow<List<Tag>>
    fun getTagsForTask(taskId: Long): Flow<List<Tag>>
    
    suspend fun getTagById(id: Long): Tag?
    suspend fun getTagsByIds(ids: List<Long>): List<Tag>
    suspend fun insertTag(tag: Tag): Long
    suspend fun insertTags(tags: List<Tag>)
    suspend fun updateTag(tag: Tag)
    suspend fun deleteTag(tag: Tag)
    suspend fun deleteTagById(id: Long)
    
    // Task-Tag relationship management
    suspend fun setTagsForTask(taskId: Long, tagIds: List<Long>)
    suspend fun addTagToTask(taskId: Long, tagId: Long)
    suspend fun removeTagFromTask(taskId: Long, tagId: Long)
    suspend fun getTagIdsForTask(taskId: Long): List<Long>
    
    // Remote sync methods (demonstrating Ktor usage)
    suspend fun syncWithRemote(): Result<List<Tag>>
    suspend fun fetchRemoteTags(): Result<List<Tag>>
}

