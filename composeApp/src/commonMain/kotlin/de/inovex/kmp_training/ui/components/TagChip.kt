package de.inovex.kmp_training.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import de.inovex.kmp_training.core.model.Tag

/**
 * A chip component for displaying a tag.
 * Can be used in read-only mode or as a selectable filter chip.
 */
@Composable
fun TagChip(
    tag: Tag,
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    onClick: (() -> Unit)? = null
) {
    val tagColor = parseHexColor(tag.colorHex) ?: MaterialTheme.colorScheme.primary
    val backgroundColor = if (isSelected) {
        tagColor.copy(alpha = 0.3f)
    } else {
        tagColor.copy(alpha = 0.1f)
    }
    val borderColor = if (isSelected) tagColor else Color.Transparent
    
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(backgroundColor)
            .border(
                width = if (isSelected) 1.5.dp else 0.dp,
                color = borderColor,
                shape = RoundedCornerShape(16.dp)
            )
            .then(
                if (onClick != null) {
                    Modifier.clickable { onClick() }
                } else {
                    Modifier
                }
            )
            .padding(horizontal = 10.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        // Color indicator dot
        Box(
            modifier = Modifier
                .size(8.dp)
                .clip(CircleShape)
                .background(tagColor)
        )
        
        // Tag name
        Text(
            text = tag.name,
            style = MaterialTheme.typography.labelMedium,
            color = tagColor
        )
        
        // Checkmark for selected state
        if (isSelected) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "Selected",
                modifier = Modifier.size(14.dp),
                tint = tagColor
            )
        }
    }
}

/**
 * A compact version of TagChip for display in lists
 */
@Composable
fun TagChipCompact(
    tag: Tag,
    modifier: Modifier = Modifier
) {
    val tagColor = parseHexColor(tag.colorHex) ?: MaterialTheme.colorScheme.primary
    
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(tagColor.copy(alpha = 0.15f))
            .padding(horizontal = 8.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Box(
            modifier = Modifier
                .size(6.dp)
                .clip(CircleShape)
                .background(tagColor)
        )
        Text(
            text = tag.name,
            style = MaterialTheme.typography.labelSmall,
            color = tagColor
        )
    }
}

private fun parseHexColor(hexColor: String): Color? {
    return try {
        val cleanHex = hexColor.removePrefix("#")
        val colorLong = cleanHex.toLong(16)
        when (cleanHex.length) {
            6 -> Color(
                red = ((colorLong shr 16) and 0xFF) / 255f,
                green = ((colorLong shr 8) and 0xFF) / 255f,
                blue = (colorLong and 0xFF) / 255f
            )
            8 -> Color(
                alpha = ((colorLong shr 24) and 0xFF) / 255f,
                red = ((colorLong shr 16) and 0xFF) / 255f,
                green = ((colorLong shr 8) and 0xFF) / 255f,
                blue = (colorLong and 0xFF) / 255f
            )
            else -> null
        }
    } catch (e: Exception) {
        null
    }
}

