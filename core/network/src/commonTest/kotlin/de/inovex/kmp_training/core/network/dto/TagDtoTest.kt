package de.inovex.kmp_training.core.network.dto

import de.inovex.kmp_training.core.model.Tag
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

/**
 * Tests for Exercise 5: TagDto
 * 
 * Run these tests to verify your TagDto implementation:
 * ./gradlew :core:network:cleanAllTests :core:network:allTests --info
 * 
 * All tests should pass when TagDto and TagListResponse are correctly implemented.
 */
class TagDtoTest {
    
    private val json = Json { ignoreUnknownKeys = true }
    
    // =========== Test 1: TagDto exists and has correct properties ===========
    
    @Test
    fun `TagDto can be created with all properties`() {
        val dto = createTagDto(
            id = 1L,
            name = "Work",
            colorHex = "#4A90D9"
        )
        
        assertEquals(1L, dto.id, "TagDto should have id = 1")
        assertEquals("Work", dto.name, "TagDto should have name = 'Work'")
        assertEquals("#4A90D9", dto.colorHex, "TagDto should have colorHex = '#4A90D9'")
    }
    
    // =========== Test 2: toDomain() conversion ===========
    
    @Test
    fun `toDomain converts TagDto to Tag correctly`() {
        val dto = createTagDto(
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
    
    // =========== Test 3: fromDomain() conversion ===========
    
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
    
    // =========== Test 4: Round-trip conversion ===========
    
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
    
    // =========== Test 5: JSON Serialization ===========
    
    @Test
    fun `TagDto can be serialized to JSON`() {
        val dto = createTagDto(
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
    
    // =========== Test 6: TagListResponse ===========
    
    @Test
    fun `TagListResponse can be created with tags and total`() {
        val tags = listOf(
            createTagDto(1L, "Work", "#4A90D9"),
            createTagDto(2L, "Personal", "#50C878")
        )
        
        val response = createTagListResponse(tags = tags, total = 2)
        
        assertEquals(2, response.tags.size, "Response should have 2 tags")
        assertEquals(2, response.total, "Response total should be 2")
    }
    
    @Test
    fun `TagListResponse can be serialized and deserialized`() {
        val jsonString = """{"tags":[{"id":1,"name":"Work","colorHex":"#4A90D9"}],"total":1}"""
        
        val response = json.decodeFromString(TagListResponse.serializer(), jsonString)
        
        assertEquals(1, response.tags.size, "Response should have 1 tag")
        assertEquals(1, response.total, "Response total should be 1")
        assertEquals("Work", response.tags[0].name, "First tag name should be 'Work'")
    }
    
    // =========== Helper functions ===========
    
    private fun createTagDto(id: Long, name: String, colorHex: String): TagDto {
        return TagDto(id = id, name = name, colorHex = colorHex)
    }
    
    private fun createTagListResponse(tags: List<TagDto>, total: Int): TagListResponse {
        return TagListResponse(tags = tags, total = total)
    }
}

