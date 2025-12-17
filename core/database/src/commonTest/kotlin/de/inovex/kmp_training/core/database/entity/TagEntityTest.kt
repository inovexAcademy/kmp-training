package de.inovex.kmp_training.core.database.entity

import de.inovex.kmp_training.core.model.Tag
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

/**
 * Tests for Exercise 2: TagEntity
 * 
 * Run these tests to verify your TagEntity implementation:
 * ./gradlew :core:database:cleanAllTests :core:database:allTests --info
 * 
 * All tests should pass when TagEntity is correctly implemented.
 */
class TagEntityTest {
    
    // =========== Test 1: TagEntity exists and has correct properties ===========
    
    @Test
    fun `TagEntity can be created with all properties`() {
        // This test verifies that TagEntity has id, name, and colorHex properties
        val entity = createTagEntity(
            id = 1L,
            name = "Work",
            colorHex = "#4A90D9"
        )
        
        assertEquals(1L, entity.id, "TagEntity should have id = 1")
        assertEquals("Work", entity.name, "TagEntity should have name = 'Work'")
        assertEquals("#4A90D9", entity.colorHex, "TagEntity should have colorHex = '#4A90D9'")
    }
    
    @Test
    fun `TagEntity default id should be 0`() {
        // This test verifies that id defaults to 0 for auto-generation
        val entity = createTagEntityWithDefaults(
            name = "Personal",
            colorHex = "#50C878"
        )
        
        assertEquals(0L, entity.id, "TagEntity default id should be 0 for auto-generation")
    }
    
    // =========== Test 2: toDomain() conversion ===========
    
    @Test
    fun `toDomain converts TagEntity to Tag correctly`() {
        val entity = createTagEntity(
            id = 42L,
            name = "Urgent",
            colorHex = "#FF6B6B"
        )
        
        val tag = entity.toDomain()
        
        assertNotNull(tag, "toDomain() should return a non-null Tag")
        assertEquals(42L, tag.id, "Tag id should match entity id")
        assertEquals("Urgent", tag.name, "Tag name should match entity name")
        assertEquals("#FF6B6B", tag.colorHex, "Tag colorHex should match entity colorHex")
    }
    
    @Test
    fun `toDomain preserves all properties`() {
        val entity = createTagEntity(
            id = 999L,
            name = "Shopping",
            colorHex = "#DDA0DD"
        )
        
        val tag = entity.toDomain()
        
        // Verify the Tag is an instance of the expected type
        assertEquals(Tag::class, tag::class, "toDomain() should return a Tag instance")
    }
    
    // =========== Test 3: fromDomain() conversion ===========
    
    @Test
    fun `fromDomain converts Tag to TagEntity correctly`() {
        val tag = Tag(
            id = 123L,
            name = "Home",
            colorHex = "#FFB347"
        )
        
        val entity = TagEntity.fromDomain(tag)
        
        assertNotNull(entity, "fromDomain() should return a non-null TagEntity")
        assertEquals(123L, entity.id, "Entity id should match tag id")
        assertEquals("Home", entity.name, "Entity name should match tag name")
        assertEquals("#FFB347", entity.colorHex, "Entity colorHex should match tag colorHex")
    }
    
    @Test
    fun `fromDomain preserves tag with id 0`() {
        val newTag = Tag(
            id = 0L,
            name = "New Tag",
            colorHex = "#45B7D1"
        )
        
        val entity = TagEntity.fromDomain(newTag)
        
        assertEquals(0L, entity.id, "Entity should preserve id = 0 for new tags")
    }
    
    // =========== Test 4: Round-trip conversion ===========
    
    @Test
    fun `round-trip conversion preserves data`() {
        val originalTag = Tag(
            id = 77L,
            name = "Travel",
            colorHex = "#BB8FCE"
        )
        
        // Convert Tag -> TagEntity -> Tag
        val entity = TagEntity.fromDomain(originalTag)
        val resultTag = entity.toDomain()
        
        assertEquals(originalTag.id, resultTag.id, "Round-trip should preserve id")
        assertEquals(originalTag.name, resultTag.name, "Round-trip should preserve name")
        assertEquals(originalTag.colorHex, resultTag.colorHex, "Round-trip should preserve colorHex")
    }
    
    // =========== Helper functions ===========
    // These functions help create TagEntity instances for testing
    // They will fail to compile if TagEntity is not properly implemented
    
    private fun createTagEntity(id: Long, name: String, colorHex: String): TagEntity {
        return TagEntity(id = id, name = name, colorHex = colorHex)
    }
    
    private fun createTagEntityWithDefaults(name: String, colorHex: String): TagEntity {
        return TagEntity(name = name, colorHex = colorHex)
    }
}

