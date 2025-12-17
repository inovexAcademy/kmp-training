package de.inovex.kmp_training.core.network.dto

import de.inovex.kmp_training.core.model.Tag
import kotlinx.serialization.Serializable

// ============================================================================
// TODO: Exercise 1 - Implement TagDto
// ============================================================================
//
// This skeleton compiles but tests will FAIL until you implement the TODOs.
//
// Your tasks:
// 1. Implement toDomain() - convert this DTO to a Tag model
// 2. Implement fromDomain() - convert a Tag model to this DTO
//
// Hints:
// - Look at TaskDto.kt for reference
// - The @Serializable annotation enables JSON serialization
//
// Run tests to verify: ./gradlew :core:network:allTests
// ============================================================================

@Serializable
data class TagDto(
    val id: Long,
    val name: String,
    val colorHex: String
) {
    /**
     * TODO: Exercise 1 - Implement this function
     * 
     * Convert this TagDto to a Tag domain model.
     */
    fun toDomain(): Tag {
        TODO("Exercise 1: Implement toDomain() - Convert TagDto to Tag")
    }
    
    companion object {
        /**
         * TODO: Exercise 1 - Implement this function
         * 
         * Convert a Tag domain model to a TagDto.
         */
        fun fromDomain(tag: Tag): TagDto {
            TODO("Exercise 1: Implement fromDomain() - Convert Tag to TagDto")
        }
    }
}

/**
 * Response wrapper for a list of tags from the API.
 * This is already implemented - no changes needed.
 */
@Serializable
data class TagListResponse(
    val tags: List<TagDto>,
    val total: Int
)
