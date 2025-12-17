package de.inovex.kmp_training.ui.components

// TODO: Exercise 9 - Create TagChip Component
//
// A reusable Compose component for displaying and selecting tags.
//
// Requirements:
// 1. Import necessary Compose libraries:
//    import androidx.compose.foundation.background
//    import androidx.compose.foundation.border
//    import androidx.compose.foundation.clickable
//    import androidx.compose.foundation.layout.Arrangement
//    import androidx.compose.foundation.layout.Box
//    import androidx.compose.foundation.layout.Row
//    import androidx.compose.foundation.layout.padding
//    import androidx.compose.foundation.layout.size
//    import androidx.compose.foundation.shape.CircleShape
//    import androidx.compose.foundation.shape.RoundedCornerShape
//    import androidx.compose.material.icons.Icons
//    import androidx.compose.material.icons.filled.Check
//    import androidx.compose.material3.Icon
//    import androidx.compose.material3.MaterialTheme
//    import androidx.compose.material3.Text
//    import androidx.compose.runtime.Composable
//    import androidx.compose.ui.Alignment
//    import androidx.compose.ui.Modifier
//    import androidx.compose.ui.draw.clip
//    import androidx.compose.ui.graphics.Color
//    import androidx.compose.ui.unit.dp
//    import de.inovex.kmp_training.core.model.Tag
//
// 2. Create a @Composable function TagChip with these parameters:
//    - tag: Tag (the tag to display)
//    - modifier: Modifier = Modifier
//    - isSelected: Boolean = false (whether the tag is selected)
//    - onClick: (() -> Unit)? = null (optional click handler)
//
// 3. Display:
//    - A colored dot based on tag.colorHex
//    - The tag name
//    - A check icon when isSelected is true
//
// 4. Styling:
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
// Example:
// @Composable
// fun TagChip(
//     tag: Tag,
//     modifier: Modifier = Modifier,
//     isSelected: Boolean = false,
//     onClick: (() -> Unit)? = null
// ) {
//     val tagColor = parseHexColor(tag.colorHex) ?: MaterialTheme.colorScheme.primary
//     val backgroundColor = if (isSelected) tagColor.copy(alpha = 0.3f) else tagColor.copy(alpha = 0.1f)
//     
//     Row(
//         modifier = modifier
//             .clip(RoundedCornerShape(16.dp))
//             .background(backgroundColor)
//             .then(if (onClick != null) Modifier.clickable { onClick() } else Modifier)
//             .padding(horizontal = 10.dp, vertical = 6.dp),
//         verticalAlignment = Alignment.CenterVertically,
//         horizontalArrangement = Arrangement.spacedBy(6.dp)
//     ) {
//         // Colored dot
//         Box(
//             modifier = Modifier
//                 .size(8.dp)
//                 .clip(CircleShape)
//                 .background(tagColor)
//         )
//         
//         // Tag name
//         Text(
//             text = tag.name,
//             style = MaterialTheme.typography.labelMedium,
//             color = tagColor
//         )
//         
//         // Check icon (only when selected)
//         if (isSelected) {
//             Icon(
//                 imageVector = Icons.Default.Check,
//                 contentDescription = "Selected",
//                 modifier = Modifier.size(14.dp),
//                 tint = tagColor
//             )
//         }
//     }
// }
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
