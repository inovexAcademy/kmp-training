package de.inovex.kmp_training.core.data.repository

import de.inovex.kmp_training.core.database.dao.TagDao
import de.inovex.kmp_training.core.model.Tag
import de.inovex.kmp_training.core.network.MockApiService
import kotlinx.coroutines.flow.Flow

// ============================================================================
// TODO: Exercise 7 - Implement TagRepositoryImpl
// ============================================================================
//
// This skeleton compiles but tests will FAIL until you implement the TODOs.
//
// Your tasks:
// 1. Implement getAllTags() - get tags from database as Flow
// 2. Implement insertTag() - insert tag into database
// 3. Implement deleteTag() - delete tag from database  
// 4. Implement fetchRemoteTags() - fetch from API, save to DB
//
// Hints:
// - Look at TaskRepositoryImpl.kt for reference
// - Use tagDao methods and convert between entities/domain models
// - Use try/catch for network calls
//
// Run tests to verify: ./gradlew :core:data:allTests
// ============================================================================

class TagRepositoryImpl(
    private val tagDao: TagDao,
    private val mockApiService: MockApiService
) : TagRepository {
    
    /**
     * TODO: Exercise 7 - Implement this function
     * 
     * Get all tags from the database as a Flow.
     * Use tagDao.getAllTags() and map entities to domain models.
     */
    override fun getAllTags(): Flow<List<Tag>> {
        TODO("Exercise 7: Implement getAllTags() - Use tagDao.getAllTags() and map to domain")
    }
    
    /**
     * TODO: Exercise 7 - Implement this function
     * 
     * Insert a tag into the database.
     * Convert the Tag to TagEntity using TagEntity.fromDomain().
     */
    override suspend fun insertTag(tag: Tag): Long {
        TODO("Exercise 7: Implement insertTag() - Convert to entity and insert")
    }
    
    /**
     * TODO: Exercise 7 - Implement this function
     * 
     * Delete a tag from the database.
     * Convert the Tag to TagEntity and delete.
     */
    override suspend fun deleteTag(tag: Tag) {
        TODO("Exercise 7: Implement deleteTag() - Convert to entity and delete")
    }
    
    /**
     * TODO: Exercise 7 - Implement this function
     * 
     * Fetch tags from the remote API and save to database.
     * Use try/catch to handle errors gracefully.
     */
    override suspend fun fetchRemoteTags(): Result<List<Tag>> {
        TODO("Exercise 7: Implement fetchRemoteTags() - Fetch from API and save to DB")
    }
}
