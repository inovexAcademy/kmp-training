package de.inovex.kmp_training.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import de.inovex.kmp_training.core.model.Tag

@Entity(tableName = "tags")
data class TagEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val colorHex: String
) {
    fun toDomain(): Tag = Tag(
        id = id,
        name = name,
        colorHex = colorHex
    )
    
    companion object {
        fun fromDomain(tag: Tag): TagEntity = TagEntity(
            id = tag.id,
            name = tag.name,
            colorHex = tag.colorHex
        )
    }
}

