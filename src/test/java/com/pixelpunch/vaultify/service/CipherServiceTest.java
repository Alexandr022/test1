package com.pixelpunch.vaultify.service;

import com.pixelpunch.vaultify.core.model.Cipher;
import com.pixelpunch.vaultify.core.model.User;
import com.pixelpunch.vaultify.core.repositories.CipherRepository;
import com.pixelpunch.vaultify.core.repositories.UserRepository;
import com.pixelpunch.vaultify.core.service.implementations.CipherService;
import com.pixelpunch.vaultify.core.utils.RSAEncryption;
import com.pixelpunch.vaultify.web.dto.CipherDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.mockito.MockitoAnnotations;

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
class CipherServiceTest {

    @Mock
    private CipherRepository cipherRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CipherService cipherService;

    @Test
    void testGetCipherById_CipherNotFound() {
        // Setup
        when(cipherRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Test
        ResponseEntity<Cipher> response = cipherService.getCipherById(1L);

        // Verify
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testGetCipherById_PrivateKeyNotFound() {
        // Setup
        Cipher cipher = new Cipher();
        cipher.setId(1L);
        cipher.setOwner(new User());
        when(cipherRepository.findById(anyLong())).thenReturn(Optional.of(cipher));
        when(userRepository.findPrivateKeyByUserId(anyLong())).thenReturn(null);

        // Test
        ResponseEntity<Cipher> response = cipherService.getCipherById(1L);

        // Verify
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testUpdateCipher_CipherNotFound() {
        // Setup
        when(cipherRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Test
        ResponseEntity<CipherDto> response = cipherService.updateCipher(1L, new CipherDto());

        // Verify
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    // Другие тесты для метода updateCipher

    @Test
    void testDeleteCipher_CipherNotFound() {
        // Setup
        when(cipherRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Test
        ResponseEntity<Void> response = cipherService.deleteCipher(1L);

        // Verify
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }


    @Test
    void testGetAllCiphers() {
        // Setup
        List<Cipher> cipherList = new ArrayList<>();
        cipherList.add(new Cipher());
        when(cipherRepository.findAll()).thenReturn(cipherList);

        // Test
        List<Cipher> result = cipherService.getAllCiphers();

        // Verify
        assertEquals(cipherList.size(), result.size());
    }

    @Test
    void testGetCiphersByOwnerId() {
        // Setup
        List<Cipher> cipherList = new ArrayList<>();
        cipherList.add(new Cipher());
        when(cipherRepository.findByOwnerId(anyLong())).thenReturn(cipherList);

        // Test
        List<Cipher> result = cipherService.getCiphersByOwnerId(1L);

        // Verify
        assertEquals(cipherList.size(), result.size());
    }

}

