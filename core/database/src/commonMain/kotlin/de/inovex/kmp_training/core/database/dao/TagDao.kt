package de.inovex.kmp_training.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import de.inovex.kmp_training.core.database.entity.TagEntity
import kotlinx.coroutines.flow.Flow

// ============================================================================
// TODO: Exercise 3 - Review and understand TagDao
// ============================================================================
//
// This DAO interface is provided for you. Study how it works:
//
// Key concepts:
// - @Dao marks this as a Room Data Access Object
// - @Query executes SQL queries
// - @Insert with OnConflictStrategy.REPLACE handles conflicts
// - @Delete removes entities
// - Flow<List<T>> provides reactive updates (no suspend needed)
// - suspend fun is used for one-time operations
//
// Your task in Exercise 3:
// - Understand how each annotation works
// - Trace how this DAO is used in TagRepositoryImpl
//
// ============================================================================

@Dao
interface TagDao {
    
    /**
     * Get all tags as a reactive Flow, sorted by name.
     * The Flow emits a new list whenever the database changes.
     */
    @Query("SELECT * FROM tags ORDER BY name ASC")
    fun getAllTags(): Flow<List<TagEntity>>
    
    /**
     * Get a single tag by ID.
     * Returns null if not found.
     */
    @Query("SELECT * FROM tags WHERE id = :id")
    suspend fun getTagById(id: Long): TagEntity?
    
    /**
     * Insert or update a tag.
     * Returns the row ID of the inserted tag.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTag(tag: TagEntity): Long
    
    /**
     * Delete a tag.
     */
    @Delete
    suspend fun deleteTag(tag: TagEntity)
    
    /**
     * Delete a tag by ID.
     */
    @Query("DELETE FROM tags WHERE id = :id")
    suspend fun deleteTagById(id: Long)
}
