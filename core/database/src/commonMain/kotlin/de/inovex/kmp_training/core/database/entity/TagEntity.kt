package de.inovex.kmp_training.core.database.entity

// TODO: Exercise 2 - Create TagEntity
//
// Room Entity for storing tags in the database.
//
// Requirements:
// 1. Import the necessary Room annotations:
//    import androidx.room.Entity
//    import androidx.room.PrimaryKey
//    import de.inovex.kmp_training.core.model.Tag
//
// 2. Annotate with @Entity(tableName = "tags")
//
// 3. Create a data class with these fields:
//    - id: Long (PrimaryKey with autoGenerate = true)
//    - name: String
//    - colorHex: String
//
// 4. Add a toDomain() function that converts this entity to a Tag model
//
// 5. Add a companion object with fromDomain(tag: Tag) function
//
// Hints:
// - Look at TaskEntity.kt and CategoryEntity.kt for reference
// - The toDomain() function should return a Tag object
// - The fromDomain() function should return a TagEntity
//
// Example structure:
// @Entity(tableName = "tags")
// data class TagEntity(
//     @PrimaryKey(autoGenerate = true)
//     val id: Long = 0,
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
//         fun fromDomain(tag: Tag): TagEntity = TagEntity(
//             id = tag.id,
//             name = tag.name,
//             colorHex = tag.colorHex
//         )
//     }
// }
//
// Implement your solution below this line:
