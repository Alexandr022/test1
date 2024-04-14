package com.pixelpunch.vaultify.dto;

import com.pixelpunch.vaultify.core.model.User;
import com.pixelpunch.vaultify.web.dto.PasswordDto;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

class PasswordDtoTest {

    @Test
    void testPasswordDtoConstructorAndGetters() {
        // Arrange
        Long id = 1L;
        String password = "password";
        int length = 8;
        boolean includeUppercase = true;
        boolean includeNumbers = true;
        boolean includeSpecial = false;
        Date generatedTime = new Date();
        User user = new User(); // Создание заглушки объекта User

        // Act
        PasswordDto passwordDto = new PasswordDto(id, password, length, includeUppercase, includeNumbers, includeSpecial, generatedTime, user);
        passwordDto.setId(id);
        passwordDto.setPassword(password);
        passwordDto.setLength(length);
        passwordDto.setIncludeUppercase(includeUppercase);
        passwordDto.setIncludeNumbers(includeNumbers);
        passwordDto.setIncludeSpecial(includeSpecial);
        passwordDto.setGeneratedTime(generatedTime);
        passwordDto.setUser(user);
        // Assert
        assertEquals(id, passwordDto.getId());
        assertEquals(password, passwordDto.getPassword());
        assertEquals(length, passwordDto.getLength());
        assertEquals(includeUppercase, passwordDto.isIncludeUppercase());
        assertEquals(includeNumbers, passwordDto.isIncludeNumbers());
        assertEquals(includeSpecial, passwordDto.isIncludeSpecial());
        assertEquals(generatedTime, passwordDto.getGeneratedTime());
        assertEquals(user, passwordDto.getUser());
    }
}

