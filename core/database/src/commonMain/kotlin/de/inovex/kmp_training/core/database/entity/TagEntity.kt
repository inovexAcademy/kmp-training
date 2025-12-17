package de.inovex.kmp_training.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import de.inovex.kmp_training.core.model.Tag

// ============================================================================
// TODO: Exercise 2 - Implement TagEntity
// ============================================================================
//
// This skeleton compiles but tests will FAIL until you implement the TODOs.
//
// Your tasks:
// 1. Implement toDomain() - convert this entity to a Tag model
// 2. Implement fromDomain() - convert a Tag model to this entity
//
// Hints:
// - Look at TaskEntity.kt and CategoryEntity.kt for reference
// - The conversion should preserve all field values
//
// Run tests to verify: ./gradlew :core:database:allTests
// ============================================================================

@Entity(tableName = "tags")
data class TagEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val colorHex: String
) {
    /**
     * TODO: Exercise 2 - Implement this function
     * 
     * Convert this TagEntity to a Tag domain model.
     * Map each field from the entity to the corresponding field in Tag.
     */
    fun toDomain(): Tag {
        TODO("Exercise 2: Implement toDomain() - Convert TagEntity to Tag")
    }
    
    companion object {
        /**
         * TODO: Exercise 2 - Implement this function
         * 
         * Convert a Tag domain model to a TagEntity.
         * Map each field from Tag to the corresponding field in TagEntity.
         */
        fun fromDomain(tag: Tag): TagEntity {
            TODO("Exercise 2: Implement fromDomain() - Convert Tag to TagEntity")
        }
    }
}
