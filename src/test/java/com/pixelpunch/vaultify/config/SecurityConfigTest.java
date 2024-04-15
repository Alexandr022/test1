package com.pixelpunch.vaultify.config;

import com.pixelpunch.vaultify.web.config.SecurityConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class SecurityConfigTest {

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private KeyPairGenerator keyPairGenerator;

    @InjectMocks
    private SecurityConfig securityConfig;

    @BeforeEach
    void setUp() throws NoSuchAlgorithmException {
        MockitoAnnotations.initMocks(this); // Initialize mocks
        // Mock any specific behavior of your dependencies if needed
        keyPairGenerator = mock(KeyPairGenerator.class);
    }

    @Test
    void testPasswordEncoderBean() {
        assertNotNull(securityConfig.passwordEncoder());
    }

    @Test
    void testKeyPairGeneratorBean() throws NoSuchAlgorithmException {
        assertNotNull(securityConfig.keyPairGenerator());
    }
}

