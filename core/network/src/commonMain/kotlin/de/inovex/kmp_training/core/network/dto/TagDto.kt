package de.inovex.kmp_training.core.network.dto

import de.inovex.kmp_training.core.model.Tag
import kotlinx.serialization.Serializable

@Serializable
data class TagDto(
    val id: Long,
    val name: String,
    val colorHex: String
) {
    fun toDomain(): Tag = Tag(
        id = id,
        name = name,
        colorHex = colorHex
    )
    
    companion object {
        fun fromDomain(tag: Tag): TagDto = TagDto(
            id = tag.id,
            name = tag.name,
            colorHex = tag.colorHex
        )
    }
}

@Serializable
data class TagListResponse(
    val tags: List<TagDto>,
    val total: Int
)

