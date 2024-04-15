package com.pixelpunch.vaultify.core.service;

import com.pixelpunch.vaultify.core.exeption.CipherNotFoundException;
import com.pixelpunch.vaultify.core.model.Cipher;
import com.pixelpunch.vaultify.web.dto.CipherDto;
import org.springframework.http.ResponseEntity;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

public interface ICipherService {
    ResponseEntity<Cipher> getCipherById(Long cipherId);

    ResponseEntity<String> createCipher(CipherDto cipherDto, Long userId) throws CipherNotFoundException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException;

    ResponseEntity<CipherDto> updateCipher(Long cipherId, CipherDto updatedCipherDTO) throws CipherNotFoundException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException;

    ResponseEntity<Void> deleteCipher(Long cipherId);

    List<Cipher> getAllCiphers();

    List<Cipher> getCiphersByOwnerId(Long ownerId);
}
