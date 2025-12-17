package de.inovex.kmp_training.core.model

import kotlinx.serialization.Serializable

/**
 * Domain model for a Tag.
 * Tags can be associated with tasks to help organize and categorize them.
 * 
 * This model is pre-provided for the training exercises.
 */
@Serializable
data class Tag(
    val id: Long = 0,
    val name: String,
    val colorHex: String
) {
    companion object {
        /**
         * Predefined tags for demonstration purposes.
         */
        val PREDEFINED_TAGS = listOf(
            Tag(id = 1, name = "Work", colorHex = "#4A90D9"),
            Tag(id = 2, name = "Personal", colorHex = "#50C878"),
            Tag(id = 3, name = "Urgent", colorHex = "#FF6B6B"),
            Tag(id = 4, name = "Home", colorHex = "#FFB347"),
            Tag(id = 5, name = "Shopping", colorHex = "#DDA0DD")
        )
        
        /**
         * Color palette for creating new tags.
         */
        val DEFAULT_COLORS = listOf(
            "#4A90D9", // Blue
            "#50C878", // Green
            "#FF6B6B", // Red
            "#FFB347", // Orange
            "#DDA0DD", // Plum
            "#45B7D1", // Cyan
            "#F7DC6F", // Gold
            "#BB8FCE"  // Purple
        )
    }
}
