package de.inovex.kmp_training.core.data.repository

import de.inovex.kmp_training.core.database.dao.TagDao
import de.inovex.kmp_training.core.database.entity.TagEntity
import de.inovex.kmp_training.core.database.entity.TaskTagCrossRef
import de.inovex.kmp_training.core.model.Tag
import de.inovex.kmp_training.core.network.MockApiService
import de.inovex.kmp_training.core.network.dto.TagDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TagRepositoryImpl(
    private val tagDao: TagDao,
    private val mockApiService: MockApiService
) : TagRepository {
    
    override fun getAllTags(): Flow<List<Tag>> {
        return tagDao.getAllTags().map { entities ->
            entities.map { it.toDomain() }
        }
    }
    
    override fun getTagsForTask(taskId: Long): Flow<List<Tag>> {
        return tagDao.getTagsForTaskFlow(taskId).map { entities ->
            entities.map { it.toDomain() }
        }
    }
    
    override suspend fun getTagById(id: Long): Tag? {
        return tagDao.getTagById(id)?.toDomain()
    }
    
    override suspend fun getTagsByIds(ids: List<Long>): List<Tag> {
        return tagDao.getTagsByIds(ids).map { it.toDomain() }
    }
    
    override suspend fun insertTag(tag: Tag): Long {
        return tagDao.insertTag(TagEntity.fromDomain(tag))
    }
    
    override suspend fun insertTags(tags: List<Tag>) {
        tagDao.insertTags(tags.map { TagEntity.fromDomain(it) })
    }
    
    override suspend fun updateTag(tag: Tag) {
        tagDao.updateTag(TagEntity.fromDomain(tag))
    }
    
    override suspend fun deleteTag(tag: Tag) {
        tagDao.deleteTag(TagEntity.fromDomain(tag))
    }
    
    override suspend fun deleteTagById(id: Long) {
        tagDao.deleteTagById(id)
    }
    
    // Task-Tag relationship management
    override suspend fun setTagsForTask(taskId: Long, tagIds: List<Long>) {
        // First, remove all existing tags for this task
        tagDao.deleteTaskTagCrossRefsByTaskId(taskId)
        
        // Then, add the new tags
        val crossRefs = tagIds.map { tagId ->
            TaskTagCrossRef(taskId = taskId, tagId = tagId)
        }
        tagDao.insertTaskTagCrossRefs(crossRefs)
    }
    
    override suspend fun addTagToTask(taskId: Long, tagId: Long) {
        tagDao.insertTaskTagCrossRef(TaskTagCrossRef(taskId = taskId, tagId = tagId))
    }
    
    override suspend fun removeTagFromTask(taskId: Long, tagId: Long) {
        tagDao.deleteTaskTagCrossRef(taskId, tagId)
    }
    
    override suspend fun getTagIdsForTask(taskId: Long): List<Long> {
        return tagDao.getTagIdsForTask(taskId)
    }
    
    override suspend fun syncWithRemote(): Result<List<Tag>> {
        return try {
            // Get local tags
            val localTags = mutableListOf<TagDto>()
            
            // Sync with remote (mock API)
            val response = mockApiService.syncTags(localTags)
            
            // Save remote tags to local database
            response.tags.forEach { tagDto ->
                tagDao.insertTag(TagEntity.fromDomain(tagDto.toDomain()))
            }
            
            Result.success(response.tags.map { it.toDomain() })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    override suspend fun fetchRemoteTags(): Result<List<Tag>> {
        return try {
            val response = mockApiService.fetchTags()
            
            // Save to local database
            response.tags.forEach { tagDto ->
                tagDao.insertTag(TagEntity.fromDomain(tagDto.toDomain()))
            }
            
            Result.success(response.tags.map { it.toDomain() })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

