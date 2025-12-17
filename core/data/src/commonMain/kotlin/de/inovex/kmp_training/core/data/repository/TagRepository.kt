package de.inovex.kmp_training.core.data.repository

import de.inovex.kmp_training.core.model.Tag
import kotlinx.coroutines.flow.Flow

// ============================================================================
// TODO: Exercise 1 - This interface is provided for you
// ============================================================================
//
// The interface is complete. Your task is to implement TagRepositoryImpl.
//
// See TagRepositoryImpl.kt for the implementation exercise.
// ============================================================================

/**
 * Repository interface for Tag operations.
 * Abstracts data sources (database and network).
 */
interface TagRepository {
    /**
     * Get all tags as a reactive Flow.
     */
    fun getAllTags(): Flow<List<Tag>>
    
    /**
     * Insert a new tag.
     * @return The ID of the inserted tag.
     */
    suspend fun insertTag(tag: Tag): Long
    
    /**
     * Delete a tag.
     */
    suspend fun deleteTag(tag: Tag)
    
    /**
     * Fetch tags from the remote API.
     * @return Result containing the list of tags or an error.
     */
    suspend fun fetchRemoteTags(): Result<List<Tag>>
}
