package de.inovex.kmp_training.core.model

data class Category(
    val id: Long = 0,
    val name: String,
    val colorHex: String
) {
    companion object {
        val DEFAULT_COLORS = listOf(
            "#FF6B6B", // Red
            "#4ECDC4", // Teal
            "#45B7D1", // Blue
            "#96CEB4", // Green
            "#FFEAA7", // Yellow
            "#DDA0DD", // Plum
            "#98D8C8", // Mint
            "#F7DC6F", // Gold
            "#BB8FCE", // Purple
            "#85C1E9"  // Sky Blue
        )
    }
}

