package de.inovex.kmp_training.core.model

enum class Priority(val displayName: String, val level: Int) {
    LOW("Low", 0),
    MEDIUM("Medium", 1),
    HIGH("High", 2);
    
    companion object {
        fun fromLevel(level: Int): Priority = entries.find { it.level == level } ?: MEDIUM
    }
}

