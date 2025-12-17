package de.inovex.kmp_training.core.database.dao

// TODO: Exercise 3 - Create TagDao
//
// Data Access Object (DAO) for tag database operations.
//
// Requirements:
// 1. Import the necessary Room annotations:
//    import androidx.room.Dao
//    import androidx.room.Delete
//    import androidx.room.Insert
//    import androidx.room.OnConflictStrategy
//    import androidx.room.Query
//    import de.inovex.kmp_training.core.database.entity.TagEntity
//    import kotlinx.coroutines.flow.Flow
//
// 2. Annotate the interface with @Dao
//
// 3. Implement these functions:
//
//    a) getAllTags(): Flow<List<TagEntity>>
//       - Returns all tags as a Flow (reactive stream)
//       - Sort by name ascending
//       - Use @Query("SELECT * FROM tags ORDER BY name ASC")
//
//    b) getTagById(id: Long): TagEntity?
//       - Suspend function that returns a single tag or null
//       - Use @Query("SELECT * FROM tags WHERE id = :id")
//
//    c) insertTag(tag: TagEntity): Long
//       - Suspend function that inserts a tag
//       - Use @Insert(onConflict = OnConflictStrategy.REPLACE)
//       - Returns the new row ID
//
//    d) deleteTag(tag: TagEntity)
//       - Suspend function that deletes a tag
//       - Use @Delete annotation
//
//    e) deleteTagById(id: Long)
//       - Suspend function that deletes by ID
//       - Use @Query("DELETE FROM tags WHERE id = :id")
//
// Hints:
// - Look at TaskDao.kt for reference patterns
// - Flow-returning functions don't need suspend keyword
// - One-time operations (get by id, insert, delete) use suspend
//
// Example:
// @Dao
// interface TagDao {
//     @Query("SELECT * FROM tags ORDER BY name ASC")
//     fun getAllTags(): Flow<List<TagEntity>>
//     
//     @Query("SELECT * FROM tags WHERE id = :id")
//     suspend fun getTagById(id: Long): TagEntity?
//     
//     @Insert(onConflict = OnConflictStrategy.REPLACE)
//     suspend fun insertTag(tag: TagEntity): Long
//     
//     @Delete
//     suspend fun deleteTag(tag: TagEntity)
//     
//     @Query("DELETE FROM tags WHERE id = :id")
//     suspend fun deleteTagById(id: Long)
// }
//
// Implement your solution below this line:
