package de.inovex.kmp_training.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import de.inovex.kmp_training.core.model.Category

@Entity(tableName = "categories")
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val colorHex: String
) {
    fun toDomain(): Category = Category(
        id = id,
        name = name,
        colorHex = colorHex
    )
    
    companion object {
        fun fromDomain(category: Category): CategoryEntity = CategoryEntity(
            id = category.id,
            name = category.name,
            colorHex = category.colorHex
        )
    }
}

