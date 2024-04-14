package com.pixelpunch.vaultify.service;

import com.pixelpunch.vaultify.core.mapper.PasswordMapper;
import com.pixelpunch.vaultify.core.model.Password;
import com.pixelpunch.vaultify.core.model.User;
import com.pixelpunch.vaultify.core.repositories.PasswordRepository;
import com.pixelpunch.vaultify.core.repositories.UserRepository;
import com.pixelpunch.vaultify.core.service.IPasswordGeneratorService;
import com.pixelpunch.vaultify.core.service.implementations.PasswordService;
import com.pixelpunch.vaultify.web.dto.PasswordDto;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class PasswordServiceTest {

    @Mock
    private PasswordRepository passwordRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private IPasswordGeneratorService passwordGeneratorService;

    @InjectMocks
    private PasswordService passwordService;

    @Test
    void testGetAllPasswords() {
        // Setup
        List<Password> passwords = List.of(new Password(), new Password());
        when(passwordRepository.findAll()).thenReturn(passwords);

        // Test
        List<Password> result = passwordService.getAllPasswords();

        // Verify
        assertEquals(passwords.size(), result.size());
    }

    @Test
    void testCreatePassword() {
        // Setup
        PasswordDto passwordDto = new PasswordDto();
        passwordDto.setLength(8);
        passwordDto.setIncludeUppercase(true);
        passwordDto.setIncludeNumbers(true);
        passwordDto.setIncludeSpecial(true);
        User user = new User();
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(passwordGeneratorService.generatePassword(anyInt(), anyBoolean(), anyBoolean(), anyBoolean())).thenReturn("TestPassword");
        Password expectedPassword = PasswordMapper.dtoToPasswords(passwordDto);
        expectedPassword.setOwner(user);
        Date date = new Date();
        expectedPassword.setGeneratedTime(date);
        when(passwordRepository.save(any(Password.class))).thenReturn(expectedPassword);

        // Test
        PasswordDto result = passwordService.createPassword(1L, passwordDto);

        // Verify
        assertNotNull(result);
        assertEquals(expectedPassword.getPasswords(), result.getPassword());
        assertEquals(expectedPassword.getOwner(), user);
        assertEquals(expectedPassword.getGeneratedTime(), date);
    }

    @Test
    void testGetPasswordById() {
        // Setup
        Password password = new Password();
        when(passwordRepository.findById(anyLong())).thenReturn(Optional.of(password));

        // Test
        PasswordDto result = passwordService.getPasswordById(1L);

        // Verify
        assertNotNull(result);
        assertEquals(password.getPasswords(), result.getPassword());
    }

    @Test
    void testUpdatePassword() {
        // Setup
        PasswordDto updatedPasswordDto = new PasswordDto();
        updatedPasswordDto.setPassword("UpdatedPassword");
        Password existingPassword = new Password();
        when(passwordRepository.findById(anyLong())).thenReturn(Optional.of(existingPassword));
        when(passwordRepository.save(any(Password.class))).thenReturn(existingPassword);

        // Test
        PasswordDto result = passwordService.updatePassword(1L, updatedPasswordDto);

        // Verify
        assertNotNull(result);
        assertEquals(updatedPasswordDto.getPassword(), result.getPassword());
    }

    @Test
    void testDeletePassword() {
        // Test
        passwordService.deletePassword(1L);

        // Verify
        verify(passwordRepository, times(1)).deleteById(1L);
    }
}

