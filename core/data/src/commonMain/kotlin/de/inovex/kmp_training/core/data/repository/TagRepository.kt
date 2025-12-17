package de.inovex.kmp_training.core.data.repository

// TODO: Exercise 7a - Create TagRepository Interface
//
// Repository interface that defines the contract for tag data operations.
// The repository pattern abstracts the data sources (database, network).
//
// Requirements:
// 1. Import necessary types:
//    import de.inovex.kmp_training.core.model.Tag
//    import kotlinx.coroutines.flow.Flow
//
// 2. Define an interface TagRepository with these functions:
//
//    a) getAllTags(): Flow<List<Tag>>
//       - Returns all tags as a reactive Flow
//
//    b) insertTag(tag: Tag): Long
//       - Suspend function that inserts a tag
//       - Returns the new tag ID
//
//    c) deleteTag(tag: Tag)
//       - Suspend function that deletes a tag
//
//    d) fetchRemoteTags(): Result<List<Tag>>
//       - Suspend function that fetches tags from the API
//       - Returns Result to handle success/failure
//
// Hints:
// - Look at TaskRepository.kt for reference
// - Flow is from kotlinx.coroutines.flow
// - Result is the Kotlin standard library type
//
// Example:
// interface TagRepository {
//     fun getAllTags(): Flow<List<Tag>>
//     suspend fun insertTag(tag: Tag): Long
//     suspend fun deleteTag(tag: Tag)
//     suspend fun fetchRemoteTags(): Result<List<Tag>>
// }
//
// Implement your solution below this line:
