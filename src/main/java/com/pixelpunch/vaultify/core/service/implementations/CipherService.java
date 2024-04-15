package com.pixelpunch.vaultify.core.service.implementations;

import com.pixelpunch.vaultify.core.exeption.CipherNotFoundException;
import com.pixelpunch.vaultify.core.mapper.CipherMapper;
import com.pixelpunch.vaultify.web.dto.CipherDto;
import lombok.extern.slf4j.Slf4j;
import com.pixelpunch.vaultify.core.model.Cipher;
import com.pixelpunch.vaultify.core.model.User;
import com.pixelpunch.vaultify.core.repositories.CipherRepository;
import com.pixelpunch.vaultify.core.repositories.UserRepository;
import com.pixelpunch.vaultify.core.utils.RSAEncryption;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.util.*;

@Slf4j
@AllArgsConstructor
@Service
public class CipherService implements com.pixelpunch.vaultify.core.service.ICipherService {

    private final CipherRepository cipherRepository;
    private final UserRepository userRepository;

    @Override
    public ResponseEntity<Cipher> getCipherById(Long cipherId) throws Exception {
        Cipher cipher = cipherRepository.findById(cipherId).orElse(null);
        if (cipher == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
            String privateKeyString = userRepository.findPrivateKeyByUserId(cipher.getOwner().getId());
            PrivateKey privateKey = RSAEncryption.getPrivateKeyFromString(privateKeyString);
            byte[] encryptedData = Base64.getDecoder().decode(cipher.getData());

            String decryptedData = RSAEncryption.decrypt(encryptedData, privateKey);
            cipher.setData(decryptedData);

        return ResponseEntity.ok().body(cipher);
    }

    @Override
    public ResponseEntity<String> createCipher(CipherDto cipherDto, Long userId) throws CipherNotFoundException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        User user = userRepository.findUserById(userId);
        String publicKeyString = userRepository.findPublicKeyById(userId);
            log.info("User ID: {}", userId);
            log.info("Encrypted Account Data: {}", cipherDto.getData());

            PublicKey publicKey = RSAEncryption.getPublicKeyFromString(publicKeyString);
            log.info("Public Key: {}", publicKeyString);

            Cipher cipher = new Cipher();
            cipher.setData(cipherDto.getData());

            byte[] encryptedBytes = RSAEncryption.encrypt(cipherDto.getData(), publicKey);

            String encryptedData = Base64.getEncoder().encodeToString(encryptedBytes);
            if (encryptedData.length() > 255) {
                encryptedData = encryptedData.substring(0, 255);
            }
            log.info("Encrypted Data: {}", encryptedData);

            cipher.setOwner(user);
            cipher.setData(encryptedData);
            cipher.setCreated(new Date());
            cipher.setLastModified(new Date());

            cipherRepository.save(cipher);

            return ResponseEntity.status(HttpStatus.CREATED).body("Cipher successfully created");
    }

    @Override
    public ResponseEntity<CipherDto> updateCipher(Long cipherId, CipherDto updatedCipherDTO) throws CipherNotFoundException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        Cipher existingCipher = cipherRepository.findById(cipherId).orElse(null);
        if (existingCipher == null) {
            throw new CipherNotFoundException("Cipher not found");
        }
            Long userId = existingCipher.getOwner().getId();
            String publicKeyString = userRepository.findPublicKeyById(userId);
            PublicKey publicKey = RSAEncryption.getPublicKeyFromString(publicKeyString);

            byte[] encryptedBytes = RSAEncryption.encrypt(updatedCipherDTO.getData(), publicKey);
            String encryptedData = Base64.getEncoder().encodeToString(encryptedBytes);
            if (encryptedData.length() > 255) {
                encryptedData = encryptedData.substring(0, 255);
            }

            existingCipher.setData(encryptedData);
            existingCipher.setLastModified(new Date());
            cipherRepository.save(existingCipher);

            CipherDto updatedCipherDto = CipherMapper.cipherToDTO(existingCipher);
            return ResponseEntity.ok().body(updatedCipherDto);

    }

    @Override
    public ResponseEntity<Void> deleteCipher(Long cipherId) {
        Cipher cipher = cipherRepository.findById(cipherId).orElse(null);
        if (cipher == null) {
            return ResponseEntity.notFound().build();
        }
        cipherRepository.delete(cipher);
        return ResponseEntity.noContent().build();
    }

    @Override
    public List<Cipher> getAllCiphers() {
        return cipherRepository.findAll();
    }

    @Override
    public List<Cipher> getCiphersByOwnerId(Long ownerId) {
        return cipherRepository.findByOwnerId(ownerId);
    }

    public void createCipherBulk(List<CipherDto> cipherDtoList, Long userId) {
        List<String> errors = new ArrayList<>();
        cipherDtoList.forEach(cipherDto -> {
            try {
                createCipher(cipherDto, userId);
            } catch (Exception e) {
                errors.add("Error creating cipher: " + e.getMessage());
            }
        });
    }

    public void updateCipherBulk(List<CipherDto> cipherDtoList) {
        List<String> errors = new ArrayList<>();
        cipherDtoList.forEach(cipherDto -> {
            try {
                Long cipherId = cipherDto.getId();
                if (cipherId != null) {
                    updateCipher(cipherId, cipherDto);
                } else {
                    errors.add("Error updating cipher: No ID provided for cipher");
                }
            } catch (Exception e) {
                errors.add("Error updating cipher: " + e.getMessage());
            }
        });
    }

}
