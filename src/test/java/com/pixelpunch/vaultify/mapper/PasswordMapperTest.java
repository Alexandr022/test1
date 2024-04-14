package com.pixelpunch.vaultify.mapper;

import com.pixelpunch.vaultify.core.mapper.PasswordMapper;
import com.pixelpunch.vaultify.core.model.Password;
import com.pixelpunch.vaultify.web.dto.PasswordDto;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PasswordMapperTest {

    @Test
    void testPasswordToDto() {
        // Arrange
        Password password = new Password();
        password.setId(1L);
        password.setPasswords("TestPassword");
        password.setLength(8);
        password.setIncludeUppercases(true);
        password.setIncludeNumbers(true);
        password.setIncludeSpecials(false);
        password.setGeneratedTime(new Date());

        // Act
        PasswordDto passwordDto = PasswordMapper.passwordToDTO(password);

        // Assert
        assertNotNull(passwordDto);
        assertEquals(password.getId(), passwordDto.getId());
        assertEquals(password.getPasswords(), passwordDto.getPassword());
        assertEquals(password.getLength(), passwordDto.getLength());
        assertEquals(password.isIncludeUppercases(), passwordDto.isIncludeUppercase());
        assertEquals(password.isIncludeNumbers(), passwordDto.isIncludeNumbers());
        assertEquals(password.isIncludeSpecials(), passwordDto.isIncludeSpecial());
        assertEquals(password.getGeneratedTime(), passwordDto.getGeneratedTime());
    }

    @Test
    void testDtoToPassword() {
        // Arrange
        PasswordDto passwordDto = new PasswordDto();
        passwordDto.setId(1L);
        passwordDto.setPassword("TestPassword");
        passwordDto.setLength(8);
        passwordDto.setIncludeUppercase(true);
        passwordDto.setIncludeNumbers(true);
        passwordDto.setIncludeSpecial(false);
        passwordDto.setGeneratedTime(new Date());

        // Act
        Password password = PasswordMapper.dtoToPasswords(passwordDto);

        // Assert
        assertNotNull(password);
        assertEquals(passwordDto.getId(), password.getId());
        assertEquals(passwordDto.getPassword(), password.getPasswords());
        assertEquals(passwordDto.getLength(), password.getLength());
        assertEquals(passwordDto.isIncludeUppercase(), password.isIncludeUppercases());
        assertEquals(passwordDto.isIncludeNumbers(), password.isIncludeNumbers());
        assertEquals(passwordDto.isIncludeSpecial(), password.isIncludeSpecials());
        assertEquals(passwordDto.getGeneratedTime(), password.getGeneratedTime());
    }
}
