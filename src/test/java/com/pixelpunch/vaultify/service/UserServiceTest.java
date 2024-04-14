package com.pixelpunch.vaultify.service;

import com.pixelpunch.vaultify.core.mapper.UserMapper;
import com.pixelpunch.vaultify.core.model.User;
import com.pixelpunch.vaultify.core.repositories.CipherRepository;
import com.pixelpunch.vaultify.core.repositories.UserRepository;
import com.pixelpunch.vaultify.core.service.IUserService;
import com.pixelpunch.vaultify.core.service.implementations.CipherService;
import com.pixelpunch.vaultify.core.service.implementations.UserService;
import com.pixelpunch.vaultify.web.dto.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private KeyPairGenerator keyPairGenerator;

    @InjectMocks
    private UserService userService;

    @Test
    void testGetUserById_UserNotFound() {
        // Setup
        Long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Test
        UserDto result = userService.getUserById(userId);

        // Verify
        assertNull(result);
        verify(userRepository, times(1)).findById(userId); // Verify that findById method is called once
    }


    @Test
    void testGetAllUsers() {
        // Setup
        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());
        when(userRepository.findAll()).thenReturn(users);

        // Test
        List<UserDto> result = userService.getAllUsers();

        // Verify
        assertEquals(users.size(), result.size());
    }

    @Test
    void testCreateUser_Success() throws Exception {
        // Setup
        UserDto createUserRequest = new UserDto();
        createUserRequest.setEmail("test@example.com");
        createUserRequest.setPassword("password123");
        createUserRequest.setPasswordHint("hint");

        // Создаем действительный KeyPair
        KeyPair keyPair = KeyPairGenerator.getInstance("RSA").generateKeyPair();

        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        // Мокируем keyPairGenerator, чтобы он возвращал действительный keyPair
        when(keyPairGenerator.generateKeyPair()).thenReturn(keyPair);

        String hashedPassword = "hashedPassword123"; // предположим, что это захешированный пароль

        User savedUser = new User();
        savedUser.setId(1L);
        savedUser.setEmail(createUserRequest.getEmail());
        savedUser.setPassword(hashedPassword); // Используем захешированный пароль
        savedUser.setPasswordHint(createUserRequest.getPasswordHint());

        when(passwordEncoder.encode(createUserRequest.getPassword())).thenReturn(hashedPassword); // Захешированный пароль
        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        // Test
        UserDto result = userService.createUser(createUserRequest);

        // Verify
        assertNotNull(result, "UserDto is null");

        assertEquals(savedUser.getId(), result.getId());
        assertEquals(savedUser.getEmail(), result.getEmail());
        assertEquals(savedUser.getPassword(), result.getPassword());
        assertEquals(savedUser.getPasswordHint(), result.getPasswordHint());
    }


    @Test
    void testCreateUser_KeyPairGenerationFailure() {
        // Setup
        UserDto createUserRequest = new UserDto();
        createUserRequest.setEmail("test@example.com");
        createUserRequest.setPassword("password123");
        createUserRequest.setPasswordHint("hint");

        KeyPair keyPair = Mockito.mock(KeyPair.class);
        when(keyPairGenerator.generateKeyPair()).thenReturn(keyPair);

        // Test
        UserDto result = userService.createUser(createUserRequest);

        // Verify
        assertNull(result, "UserDto should be null");
    }

    @Test
    void testUpdateUser() {
        // Setup
        UserDto userDto = new UserDto();
        userDto.setId(1L);
        User user = new User();
        user.setId(userDto.getId());
        when(userRepository.save(any(User.class))).thenReturn(user);

        // Test
        UserDto result = userService.updateUser(userDto);

        // Verify
        assertNotNull(result);
        assertEquals(userDto.getId(), result.getId());
    }

    @Test
    void testDeleteUser() {
        // Test
        userService.deleteUser(1L);

        // Verify
        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    void testGetPublicKey() {
        // Setup
        when(userRepository.findPublicKeyById(anyLong())).thenReturn("publicKey");

        // Test
        String result = userService.getPublicKey(1L);

        // Verify
        assertNotNull(result);
        assertEquals("publicKey", result);
    }
}

