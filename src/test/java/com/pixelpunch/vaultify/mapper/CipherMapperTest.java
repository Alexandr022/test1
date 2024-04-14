package com.pixelpunch.vaultify.mapper;

import com.pixelpunch.vaultify.core.mapper.CipherMapper;
import com.pixelpunch.vaultify.core.model.Cipher;
import com.pixelpunch.vaultify.web.dto.CipherDto;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CipherMapperTest {

    @Test
    void testCipherToDto() {
        // Arrange
        Cipher cipher = new Cipher();
        cipher.setId(1L);
        cipher.setData("Test data");
        cipher.setFavorite(true);
        cipher.setCreated(new Date());
        cipher.setLastModified(new Date());

        // Act
        CipherDto cipherDto = CipherMapper.cipherToDTO(cipher);

        // Assert
        assertNotNull(cipherDto);
        assertEquals(cipher.getId(), cipherDto.getId());
        assertEquals(cipher.getData(), cipherDto.getData());
        assertEquals(cipher.isFavorite(), cipherDto.isFavorite());
        assertEquals(cipher.getCreated(), cipherDto.getCreated());
        assertEquals(cipher.getLastModified(), cipherDto.getLastModified());
    }

    @Test
    void testDtoToCipher() {
        // Arrange
        CipherDto cipherDto = new CipherDto();
        cipherDto.setId(1L);
        cipherDto.setData("Test data");
        cipherDto.setFavorite(true);
        cipherDto.setCreated(new Date());
        cipherDto.setLastModified(new Date());

        // Act
        Cipher cipher = CipherMapper.dtoToCipher(cipherDto);

        // Assert
        assertNotNull(cipher);
        assertEquals(cipherDto.getId(), cipher.getId());
        assertEquals(cipherDto.getData(), cipher.getData());
        assertEquals(cipherDto.isFavorite(), cipher.isFavorite());
        assertEquals(cipherDto.getCreated(), cipher.getCreated());
        assertEquals(cipherDto.getLastModified(), cipher.getLastModified());
    }
}

