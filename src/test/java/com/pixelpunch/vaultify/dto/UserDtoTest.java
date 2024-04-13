package com.pixelpunch.vaultify.dto;

import com.pixelpunch.vaultify.web.dto.CipherDto;
import com.pixelpunch.vaultify.web.dto.PasswordDto;
import com.pixelpunch.vaultify.web.dto.UserDto;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class UserDtoTest {

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
        List<CipherDto> ciphers = List.of(new CipherDto());
        List<PasswordDto> passwords = List.of(new PasswordDto());

        UserDto userDto = new UserDto(id, email, emailVerified, emailVerificationCode, emailVerificationCodeExpiresAt, password, privateKey,
                publicKey, passwordHint, twoFactorEnabled, twoFactorVerificationCode, created, lastPasswordChange, ciphers, passwords);
        userDto.setTwoFactorEnabled(twoFactorEnabled);
        userDto.setTwoFactorVerificationCode(twoFactorVerificationCode);
        userDto.setCreated(created);
        userDto.setLastPasswordChange(lastPasswordChange);
        userDto.setCiphers(ciphers);
        userDto.setPasswords(passwords);

        assertNotNull(userDto);
        assertEquals(id, userDto.getId());
        assertEquals(email, userDto.getEmail());
        assertEquals(emailVerified, userDto.isEmailVerified());
        assertEquals(emailVerificationCode, userDto.getEmailVerificationCode());
        assertEquals(emailVerificationCodeExpiresAt, userDto.getEmailVerificationCodeExpiresAt());
        assertEquals(password, userDto.getPassword());
        assertEquals(privateKey, userDto.getPrivateKey());
        assertEquals(publicKey, userDto.getPublicKey());
        assertEquals(passwordHint, userDto.getPasswordHint());
        assertEquals(twoFactorEnabled, userDto.isTwoFactorEnabled());
        assertEquals(twoFactorVerificationCode, userDto.getTwoFactorVerificationCode());
        assertEquals(created, userDto.getCreated());
        assertEquals(lastPasswordChange, userDto.getLastPasswordChange());
        assertEquals(ciphers, userDto.getCiphers());
        assertEquals(passwords, userDto.getPasswords());
    }
}

