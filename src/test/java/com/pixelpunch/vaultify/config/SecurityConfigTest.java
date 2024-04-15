package com.pixelpunch.vaultify.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class SecurityConfigTest {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private KeyPairGenerator keyPairGenerator;

    @Test
    void testPasswordEncoderBean() {
        assertNotNull(passwordEncoder);
    }

    @Test
    void testKeyPairGeneratorBean() throws NoSuchAlgorithmException {
        assertNotNull(keyPairGenerator);
    }
}

