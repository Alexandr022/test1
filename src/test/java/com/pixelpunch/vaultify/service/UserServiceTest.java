package com.pixelpunch.vaultify.service;

import com.pixelpunch.vaultify.core.mapper.UserMapper;
import com.pixelpunch.vaultify.core.model.User;
import com.pixelpunch.vaultify.core.repositories.UserRepository;
import com.pixelpunch.vaultify.core.service.IUserService;
import com.pixelpunch.vaultify.core.service.implementations.UserService;
import com.pixelpunch.vaultify.web.dto.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private KeyPairGenerator keyPairGenerator;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() throws Exception {
        KeyPair keyPair = KeyPairGenerator.getInstance("RSA").generateKeyPair();
        when(keyPairGenerator.generateKeyPair()).thenReturn(keyPair);
    }

    @Test
    void testGetUserById() {
        // Setup
        User user = new User();
        user.setId(1L);
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        // Test
        UserDto result = userService.getUserById(1L);

        // Verify
        assertNotNull(result);
        assertEquals(user.getId(), result.getId());
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
    void testCreateUser() {
        // Setup
        UserDto userDto = new UserDto();
        userDto.setEmail("test@example.com");
        userDto.setPassword("password");
        userDto.setPasswordHint("hint");

        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword("encodedPassword");
        user.setPasswordHint(userDto.getPasswordHint());
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");

        // Test
        UserDto result = userService.createUser(userDto);

        // Verify
        assertNotNull(result);
        assertEquals(userDto.getEmail(), result.getEmail());
        assertEquals(userDto.getPasswordHint(), result.getPasswordHint());
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

