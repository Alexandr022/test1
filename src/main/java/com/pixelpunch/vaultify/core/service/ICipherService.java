package com.pixelpunch.vaultify.core.service;

import com.pixelpunch.vaultify.core.model.Cipher;
import com.pixelpunch.vaultify.web.dto.CipherDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ICipherService {
    ResponseEntity<Cipher> getCipherById(Long cipherId) throws Exception;

    ResponseEntity<String> createCipher(CipherDto cipherDto, Long userId) throws Exception;

    ResponseEntity<CipherDto> updateCipher(Long cipherId, CipherDto updatedCipherDTO) throws Exception;

    ResponseEntity<Void> deleteCipher(Long cipherId);

    List<Cipher> getAllCiphers();

    List<Cipher> getCiphersByOwnerId(Long ownerId);
}
