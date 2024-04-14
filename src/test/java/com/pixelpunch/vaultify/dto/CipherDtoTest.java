package com.pixelpunch.vaultify.dto;

import com.pixelpunch.vaultify.core.model.User;
import com.pixelpunch.vaultify.web.dto.CipherDto;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

class CipherDtoTest {

    @Test
    void testCipherDtoConstructorAndGetters() {
        // Arrange
        Long id = 1L;
        User owner = new User(); // Создание заглушки объекта User
        String data = "data";
        boolean favorite = true;
        Date created = new Date();
        Date lastModified = new Date();

        // Act
        CipherDto cipherDto = new CipherDto(id, owner, data, favorite, created, lastModified);
        cipherDto.setId(id);
        cipherDto.setOwner(owner);
        cipherDto.setData(data);
        cipherDto.setFavorite(favorite);
        cipherDto.setCreated(created);
        cipherDto.setLastModified(lastModified);
        // Assert
        assertEquals(id, cipherDto.getId());
        assertEquals(owner, cipherDto.getOwner());
        assertEquals(data, cipherDto.getData());
        assertEquals(favorite, cipherDto.isFavorite());
        assertEquals(created, cipherDto.getCreated());
        assertEquals(lastModified, cipherDto.getLastModified());
    }
}

