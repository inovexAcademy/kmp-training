package de.inovex.kmp_training.core.network.dto

import de.inovex.kmp_training.core.model.Tag
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

/**
 * Tests for Exercise 1: TagDto
 * 
 * Run these tests to verify your implementation:
 * ./gradlew :core:network:allTests
 * 
 * These tests will FAIL until you implement toDomain() and fromDomain() in TagDto.
 */
class TagDtoTest {
    
    private val json = Json { ignoreUnknownKeys = true }
    
    // =========== toDomain() Tests ===========
    
    @Test
    fun `toDomain converts TagDto to Tag correctly`() {
        val dto = TagDto(
            id = 42L,
            name = "Urgent",
            colorHex = "#FF6B6B"
        )
        
        val tag = dto.toDomain()
        
        assertNotNull(tag, "toDomain() should return a non-null Tag")
        assertEquals(42L, tag.id, "Tag id should match dto id")
        assertEquals("Urgent", tag.name, "Tag name should match dto name")
        assertEquals("#FF6B6B", tag.colorHex, "Tag colorHex should match dto colorHex")
    }
    
    // =========== fromDomain() Tests ===========
    
    @Test
    fun `fromDomain converts Tag to TagDto correctly`() {
        val tag = Tag(
            id = 123L,
            name = "Home",
            colorHex = "#FFB347"
        )
        
        val dto = TagDto.fromDomain(tag)
        
        assertNotNull(dto, "fromDomain() should return a non-null TagDto")
        assertEquals(123L, dto.id, "Dto id should match tag id")
        assertEquals("Home", dto.name, "Dto name should match tag name")
        assertEquals("#FFB347", dto.colorHex, "Dto colorHex should match tag colorHex")
    }
    
    // =========== Round-trip Tests ===========
    
    @Test
    fun `round-trip conversion preserves data`() {
        val originalTag = Tag(
            id = 77L,
            name = "Travel",
            colorHex = "#BB8FCE"
        )
        
        // Convert Tag -> TagDto -> Tag
        val dto = TagDto.fromDomain(originalTag)
        val resultTag = dto.toDomain()
        
        assertEquals(originalTag.id, resultTag.id, "Round-trip should preserve id")
        assertEquals(originalTag.name, resultTag.name, "Round-trip should preserve name")
        assertEquals(originalTag.colorHex, resultTag.colorHex, "Round-trip should preserve colorHex")
    }
    
    // =========== JSON Serialization Tests ===========
    
    @Test
    fun `TagDto can be serialized to JSON`() {
        val dto = TagDto(
            id = 5L,
            name = "Shopping",
            colorHex = "#DDA0DD"
        )
        
        val jsonString = json.encodeToString(TagDto.serializer(), dto)
        
        assertTrue(jsonString.contains("\"id\":5"), "JSON should contain id")
        assertTrue(jsonString.contains("\"name\":\"Shopping\""), "JSON should contain name")
        assertTrue(jsonString.contains("\"colorHex\":\"#DDA0DD\""), "JSON should contain colorHex")
    }
    
    @Test
    fun `TagDto can be deserialized from JSON`() {
        val jsonString = """{"id":10,"name":"Fitness","colorHex":"#45B7D1"}"""
        
        val dto = json.decodeFromString(TagDto.serializer(), jsonString)
        
        assertEquals(10L, dto.id, "Deserialized id should be 10")
        assertEquals("Fitness", dto.name, "Deserialized name should be 'Fitness'")
        assertEquals("#45B7D1", dto.colorHex, "Deserialized colorHex should be '#45B7D1'")
    }
    
    // =========== TagListResponse Tests ===========
    
    @Test
    fun `TagListResponse can hold multiple tags`() {
        val tags = listOf(
            TagDto(1L, "Work", "#4A90D9"),
            TagDto(2L, "Personal", "#50C878")
        )
        
        val response = TagListResponse(tags = tags, total = 2)
        
        assertEquals(2, response.tags.size, "Response should have 2 tags")
        assertEquals(2, response.total, "Response total should be 2")
    }
    
    @Test
    fun `TagListResponse can be deserialized from JSON`() {
        val jsonString = """{"tags":[{"id":1,"name":"Work","colorHex":"#4A90D9"}],"total":1}"""
        
        val response = json.decodeFromString(TagListResponse.serializer(), jsonString)
        
        assertEquals(1, response.tags.size, "Response should have 1 tag")
        assertEquals(1, response.total, "Response total should be 1")
        assertEquals("Work", response.tags[0].name, "First tag name should be 'Work'")
    }
}

