package com.pixelpunch.vaultify.model;

import com.pixelpunch.vaultify.core.model.Cipher;
import com.pixelpunch.vaultify.core.model.User;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CipherTest {

    @Test
    void testConstructorAndGetters() {
        Long id = 1L;
        User owner = new User("test@example.com");
        String data = "cipher data";
        boolean favorite = true;
        Date created = new Date();
        Date lastModified = new Date();

        Cipher cipher = new Cipher(id, owner, data, favorite, created, lastModified);

        cipher.setId(id);
        cipher.setOwner(owner);
        cipher.setData(data);
        cipher.setFavorite(favorite);
        cipher.setCreated(created);
        cipher.setLastModified(lastModified);

        assertNotNull(cipher);
        assertEquals(id, cipher.getId());
        assertEquals(owner, cipher.getOwner());
        assertEquals(data, cipher.getData());
        assertEquals(favorite, cipher.isFavorite());
        assertEquals(created, cipher.getCreated());
        assertEquals(lastModified, cipher.getLastModified());
    }
}
