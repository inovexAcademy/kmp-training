package de.inovex.kmp_training.core.network.dto

// TODO: Exercise 5 - Create TagDto
//
// Data Transfer Object for tag API communication.
// Used to serialize/deserialize tags when communicating with the API.
//
// Requirements:
// 1. Import necessary libraries:
//    import de.inovex.kmp_training.core.model.Tag
//    import kotlinx.serialization.Serializable
//
// 2. Create a data class TagDto with:
//    - id: Long
//    - name: String
//    - colorHex: String
//
// 3. Annotate with @Serializable
//
// 4. Add a toDomain() function that converts to Tag model
//
// 5. Add a companion object with fromDomain(tag: Tag) function
//
// 6. Create a TagListResponse data class:
//    - tags: List<TagDto>
//    - total: Int
//    - Also annotate with @Serializable
//
// Hints:
// - Look at TaskDto.kt for reference
// - The @Serializable annotation is from kotlinx.serialization
//
// Example:
// @Serializable
// data class TagDto(
//     val id: Long,
//     val name: String,
//     val colorHex: String
// ) {
//     fun toDomain(): Tag = Tag(
//         id = id,
//         name = name,
//         colorHex = colorHex
//     )
//     
//     companion object {
//         fun fromDomain(tag: Tag): TagDto = TagDto(
//             id = tag.id,
//             name = tag.name,
//             colorHex = tag.colorHex
//         )
//     }
// }
//
// @Serializable
// data class TagListResponse(
//     val tags: List<TagDto>,
//     val total: Int
// )
//
// Implement your solution below this line:
