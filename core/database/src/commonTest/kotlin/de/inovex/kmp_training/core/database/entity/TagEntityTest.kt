package de.inovex.kmp_training.core.database.entity

import de.inovex.kmp_training.core.model.Tag
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

/**
 * Tests for Exercise 2: TagEntity
 * 
 * Run these tests to verify your implementation:
 * ./gradlew :core:database:allTests
 * 
 * These tests will FAIL until you implement toDomain() and fromDomain() in TagEntity.
 */
class TagEntityTest {
    
    // =========== toDomain() Tests ===========
    
    @Test
    fun `toDomain converts TagEntity to Tag correctly`() {
        val entity = TagEntity(
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
    fun `toDomain preserves id of 0`() {
        val entity = TagEntity(
            id = 0L,
            name = "New Tag",
            colorHex = "#45B7D1"
        )
        
        val tag = entity.toDomain()
        
        assertEquals(0L, tag.id, "Tag should preserve id = 0")
    }
    
    // =========== fromDomain() Tests ===========
    
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
    
    // =========== Round-trip Tests ===========
    
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
    
    @Test
    fun `entity to domain and back preserves data`() {
        val originalEntity = TagEntity(
            id = 99L,
            name = "Work",
            colorHex = "#4A90D9"
        )
        
        // Convert TagEntity -> Tag -> TagEntity
        val tag = originalEntity.toDomain()
        val resultEntity = TagEntity.fromDomain(tag)
        
        assertEquals(originalEntity.id, resultEntity.id, "Round-trip should preserve id")
        assertEquals(originalEntity.name, resultEntity.name, "Round-trip should preserve name")
        assertEquals(originalEntity.colorHex, resultEntity.colorHex, "Round-trip should preserve colorHex")
    }
}

