package de.inovex.kmp_training.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import de.inovex.kmp_training.core.database.entity.TagEntity
import de.inovex.kmp_training.core.database.entity.TaskTagCrossRef
import kotlinx.coroutines.flow.Flow

@Dao
interface TagDao {
    
    @Query("SELECT * FROM tags ORDER BY name ASC")
    fun getAllTags(): Flow<List<TagEntity>>
    
    @Query("SELECT * FROM tags WHERE id = :id")
    suspend fun getTagById(id: Long): TagEntity?
    
    @Query("SELECT * FROM tags WHERE id IN (:ids)")
    suspend fun getTagsByIds(ids: List<Long>): List<TagEntity>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTag(tag: TagEntity): Long
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTags(tags: List<TagEntity>)
    
    @Update
    suspend fun updateTag(tag: TagEntity)
    
    @Delete
    suspend fun deleteTag(tag: TagEntity)
    
    @Query("DELETE FROM tags WHERE id = :id")
    suspend fun deleteTagById(id: Long)
    
    // Cross-reference operations for Task-Tag relationship
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTaskTagCrossRef(crossRef: TaskTagCrossRef)
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTaskTagCrossRefs(crossRefs: List<TaskTagCrossRef>)
    
    @Query("DELETE FROM task_tag_cross_ref WHERE taskId = :taskId")
    suspend fun deleteTaskTagCrossRefsByTaskId(taskId: Long)
    
    @Query("DELETE FROM task_tag_cross_ref WHERE taskId = :taskId AND tagId = :tagId")
    suspend fun deleteTaskTagCrossRef(taskId: Long, tagId: Long)
    
    @Query("SELECT tagId FROM task_tag_cross_ref WHERE taskId = :taskId")
    suspend fun getTagIdsForTask(taskId: Long): List<Long>
    
    @Query("SELECT tagId FROM task_tag_cross_ref WHERE taskId = :taskId")
    fun getTagIdsForTaskFlow(taskId: Long): Flow<List<Long>>
    
    @Query("""
        SELECT tags.* FROM tags 
        INNER JOIN task_tag_cross_ref ON tags.id = task_tag_cross_ref.tagId 
        WHERE task_tag_cross_ref.taskId = :taskId
    """)
    suspend fun getTagsForTask(taskId: Long): List<TagEntity>
    
    @Query("""
        SELECT tags.* FROM tags 
        INNER JOIN task_tag_cross_ref ON tags.id = task_tag_cross_ref.tagId 
        WHERE task_tag_cross_ref.taskId = :taskId
    """)
    fun getTagsForTaskFlow(taskId: Long): Flow<List<TagEntity>>
}

