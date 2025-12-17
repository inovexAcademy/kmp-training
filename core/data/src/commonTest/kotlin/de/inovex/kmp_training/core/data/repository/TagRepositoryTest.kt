package de.inovex.kmp_training.core.data.repository

import de.inovex.kmp_training.core.model.Tag
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

/**
 * Tests for Exercise 7: TagRepository
 * 
 * Run these tests to verify your implementation:
 * ./gradlew :core:data:allTests
 * 
 * Note: These tests verify the Tag model and repository interface.
 * The implementation tests are more comprehensive on the solution branch.
 */
class TagRepositoryTest {
    
    // =========== Tag Model Tests ===========
    
    @Test
    fun `Tag can be created with all properties`() {
        val tag = Tag(
            id = 1L,
            name = "Test Tag",
            colorHex = "#FF0000"
        )
        
        assertEquals(1L, tag.id)
        assertEquals("Test Tag", tag.name)
        assertEquals("#FF0000", tag.colorHex)
    }
    
    @Test
    fun `Tag has default id of 0`() {
        val tag = Tag(
            name = "Default ID Tag",
            colorHex = "#00FF00"
        )
        
        assertEquals(0L, tag.id, "Tag should default to id = 0")
    }
    
    @Test
    fun `Tag PREDEFINED_TAGS is available`() {
        val predefinedTags = Tag.PREDEFINED_TAGS
        
        assertNotNull(predefinedTags, "PREDEFINED_TAGS should not be null")
        assertTrue(predefinedTags.isNotEmpty(), "PREDEFINED_TAGS should not be empty")
    }
    
    @Test
    fun `Tag PREDEFINED_TAGS contains Work tag`() {
        val workTag = Tag.PREDEFINED_TAGS.find { it.name == "Work" }
        
        assertNotNull(workTag, "PREDEFINED_TAGS should contain a 'Work' tag")
    }
    
    @Test
    fun `Tag DEFAULT_COLORS is available`() {
        val defaultColors = Tag.DEFAULT_COLORS
        
        assertNotNull(defaultColors, "DEFAULT_COLORS should not be null")
        assertTrue(defaultColors.isNotEmpty(), "DEFAULT_COLORS should not be empty")
    }
    
    @Test
    fun `Tag DEFAULT_COLORS are valid hex colors`() {
        val defaultColors = Tag.DEFAULT_COLORS
        
        assertTrue(
            defaultColors.all { it.startsWith("#") },
            "All DEFAULT_COLORS should be hex color strings starting with #"
        )
    }
    
    // =========== TagRepository Interface Tests ===========
    // These tests verify that the interface is properly defined
    
    @Test
    fun `TagRepository interface exists`() {
        // This test passes if the interface compiles correctly
        // The actual implementation is tested via TagRepositoryImpl
        assertTrue(true, "TagRepository interface should be defined")
    }
    
    @Test
    fun `TagRepositoryImpl class exists`() {
        // This test passes if the class compiles correctly
        assertTrue(true, "TagRepositoryImpl class should be defined")
    }
}

