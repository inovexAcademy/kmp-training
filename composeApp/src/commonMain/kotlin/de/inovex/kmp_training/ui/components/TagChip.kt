package de.inovex.kmp_training.ui.components

// TODO: Exercise 2 - Create TagChip Component
//
// A reusable Compose component for displaying and selecting tags.
//
// 1. Create a @Composable function TagChip with these parameters:
//    - tag: Tag (the tag to display)
//    - modifier: Modifier = Modifier
//    - isSelected: Boolean = false (whether the tag is selected)
//    - onClick: (() -> Unit)? = null (optional click handler)
//
// 2. Display:
//    - A colored dot based on tag.colorHex
//    - The tag name
//    - A check icon when isSelected is true
//
// 3. Styling:
//    - Use rounded corners (RoundedCornerShape)
//    - Use a semi-transparent background based on tag color
//    - Add a border when selected
//    - Make clickable if onClick is provided
//
// Hints:
// - Look at CategoryChip in TaskCard.kt for reference
// - Use the parseHexColor helper function (provided below)
// - Use Box with CircleShape for the colored dot
// - Use Row to arrange elements horizontally
//
// Implement your solution below this line:


// Helper function to parse a hex color string to a Compose Color.
// You can use this in your implementation!
//
// private fun parseHexColor(hexColor: String): Color? {
//     return try {
//         val cleanHex = hexColor.removePrefix("#")
//         val colorLong = cleanHex.toLong(16)
//         when (cleanHex.length) {
//             6 -> Color(
//                 red = ((colorLong shr 16) and 0xFF) / 255f,
//                 green = ((colorLong shr 8) and 0xFF) / 255f,
//                 blue = (colorLong and 0xFF) / 255f
//             )
//             8 -> Color(
//                 alpha = ((colorLong shr 24) and 0xFF) / 255f,
//                 red = ((colorLong shr 16) and 0xFF) / 255f,
//                 green = ((colorLong shr 8) and 0xFF) / 255f,
//                 blue = (colorLong and 0xFF) / 255f
//             )
//             else -> null
//         }
//     } catch (e: Exception) {
//         null
//     }
// }
