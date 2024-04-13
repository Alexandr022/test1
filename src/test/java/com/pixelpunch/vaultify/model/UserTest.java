package com.pixelpunch.vaultify.model;

import com.pixelpunch.vaultify.core.model.Cipher;
import com.pixelpunch.vaultify.core.model.Password;
import com.pixelpunch.vaultify.core.model.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class UserTest {

    @Test
    void testConstructorAndGetters() {
        Long id = 1L;
        String email = "test@example.com";
        boolean emailVerified = true;
        String emailVerificationCode = "verificationCode";
        Date emailVerificationCodeExpiresAt = new Date();
        String password = "password";
        String privateKey = "privateKey";
        String publicKey = "publicKey";
        String passwordHint = "passwordHint";
        boolean twoFactorEnabled = true;
        String twoFactorVerificationCode = "twoFactorCode";
        Date created = new Date();
        Date lastPasswordChange = new Date();
        List<Cipher> ciphers = new ArrayList<>();
        List<Password> passwords = new ArrayList<>();

        User user = new User(id, email, emailVerified, emailVerificationCode, emailVerificationCodeExpiresAt, password,
                privateKey, publicKey, passwordHint, twoFactorEnabled, twoFactorVerificationCode, created,
                lastPasswordChange, ciphers, passwords);

        user.setId(id);
        user.setEmail(email);
        user.setEmailVerified(emailVerified);
        user.setEmailVerificationCode(emailVerificationCode);
        user.setEmailVerificationCodeExpiresAt(emailVerificationCodeExpiresAt);
        user.setPassword(password);
        user.setPrivateKey(privateKey);
        user.setPublicKey(publicKey);
        user.setPasswordHint(passwordHint);
        user.setTwoFactorEnabled(twoFactorEnabled);
        user.setTwoFactorVerificationCode(twoFactorVerificationCode);
        user.setCreated(created);
        user.setLastPasswordChange(lastPasswordChange);
        user.setCiphers(ciphers);
        user.setPasswords(passwords);

        assertNotNull(user);
        assertEquals(id, user.getId());
        assertEquals(email, user.getEmail());
        assertEquals(emailVerified, user.isEmailVerified());
        assertEquals(emailVerificationCode, user.getEmailVerificationCode());
        assertEquals(emailVerificationCodeExpiresAt, user.getEmailVerificationCodeExpiresAt());
        assertEquals(password, user.getPassword());
        assertEquals(privateKey, user.getPrivateKey());
        assertEquals(publicKey, user.getPublicKey());
        assertEquals(passwordHint, user.getPasswordHint());
        assertEquals(twoFactorEnabled, user.isTwoFactorEnabled());
        assertEquals(twoFactorVerificationCode, user.getTwoFactorVerificationCode());
        assertEquals(created, user.getCreated());
        assertEquals(lastPasswordChange, user.getLastPasswordChange());
        assertEquals(ciphers, user.getCiphers());
        assertEquals(passwords, user.getPasswords());
    }
}
