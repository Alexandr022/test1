package com.pixelpunch.vaultify.utils;

import com.pixelpunch.vaultify.core.utils.RSAEncryption;
import org.junit.jupiter.api.Test;

import java.security.*;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.*;

class RSAEncryptionTest {

    @Test
    void testEncryptAndDecrypt() throws Exception {
        // Генерируем ключевую пару
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        // Шифруем данные
        String originalData = "Hello, world!";
        byte[] encryptedData = RSAEncryption.encrypt(originalData, publicKey);

        // Расшифровываем данные
        String decryptedData = RSAEncryption.decrypt(encryptedData, privateKey);

        // Проверяем, что данные после расшифровки совпадают с исходными данными
        assertEquals(originalData, decryptedData);
    }

    @Test
    void testGetPublicKeyFromString() throws Exception {
        // Генерируем ключевую пару
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        PublicKey publicKey = keyPair.getPublic();

        // Кодируем открытый ключ в строку
        String publicKeyString = Base64.getEncoder().encodeToString(publicKey.getEncoded());

        // Получаем открытый ключ из строки
        PublicKey decodedPublicKey = RSAEncryption.getPublicKeyFromString(publicKeyString);

        // Проверяем, что полученный открытый ключ совпадает с исходным
        assertArrayEquals(publicKey.getEncoded(), decodedPublicKey.getEncoded());
    }

    @Test
    void testGetPrivateKeyFromString() throws Exception {
        // Генерируем ключевую пару
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();

        // Кодируем закрытый ключ в строку
        String privateKeyString = Base64.getEncoder().encodeToString(privateKey.getEncoded());

        // Получаем закрытый ключ из строки
        PrivateKey decodedPrivateKey = RSAEncryption.getPrivateKeyFromString(privateKeyString);

        // Проверяем, что полученный закрытый ключ совпадает с исходным
        assertArrayEquals(privateKey.getEncoded(), decodedPrivateKey.getEncoded());
    }
}

