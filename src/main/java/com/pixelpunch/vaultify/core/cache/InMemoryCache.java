package com.pixelpunch.vaultify.core.cache;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@NoArgsConstructor
@Component
public class InMemoryCache<K, V> {
    private final Map<K, V> cache = new ConcurrentHashMap<>();

    public void put(K key, V value) {
        cache.put(key, value);
    }

    public V get(K key) {
        return cache.get(key);
    }

    public boolean containsKey(K key) {
        return cache.containsKey(key);
    }

    public void remove(K key) {
        cache.remove(key);
    }

    public int getSize() {
        return cache.size();
    }
}

