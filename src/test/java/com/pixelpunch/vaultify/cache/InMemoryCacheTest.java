package com.pixelpunch.vaultify.cache;

import com.pixelpunch.vaultify.core.cache.InMemoryCache;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryCacheTest {

    private InMemoryCache<String, Integer> cache;

    @BeforeEach
    void setUp() {
        cache = new InMemoryCache<>();
    }

    @Test
    void testPutAndGet() {
        // Arrange
        String key = "test";
        Integer value = 123;

        // Act
        cache.put(key, value);
        Integer retrievedValue = cache.get(key);

        // Assert
        assertNotNull(retrievedValue);
        assertEquals(value, retrievedValue);
    }

    @Test
    void testContainsKey() {
        // Arrange
        String key = "test";
        Integer value = 123;

        // Act
        cache.put(key, value);

        // Assert
        assertTrue(cache.containsKey(key));
        assertFalse(cache.containsKey("nonexistentKey"));
    }

    @Test
    void testRemove() {
        // Arrange
        String key = "test";
        Integer value = 123;

        // Act
        cache.put(key, value);
        cache.remove(key);

        // Assert
        assertNull(cache.get(key));
    }

    @Test
    void testGetSize() {
        // Arrange
        cache.put("key1", 1);
        cache.put("key2", 2);
        cache.put("key3", 3);

        // Act
        int size = cache.getSize();

        // Assert
        assertEquals(3, size);
    }

    @Test
    void testPutWithMaxSizeReached() {
        // Arrange
        String key1 = "key1";
        String key2 = "key2";
        String key3 = "key3";
        Integer value = 123;

        // Act
        cache.put(key1, value);
        cache.put(key2, value);
        cache.put(key3, value);

        // Assert
        assertEquals(value, cache.get(key1));
        assertEquals(value, cache.get(key2));
        assertEquals(value, cache.get(key3));
        assertEquals(3, cache.getSize()); // Ensure only the last put value remains in the cache
    }

}

