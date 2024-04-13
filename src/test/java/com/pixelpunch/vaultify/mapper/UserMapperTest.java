package com.pixelpunch.vaultify.mapper;

import com.pixelpunch.vaultify.core.mapper.UserMapper;
import com.pixelpunch.vaultify.core.model.Cipher;
import com.pixelpunch.vaultify.core.model.Password;
import com.pixelpunch.vaultify.core.model.User;
import com.pixelpunch.vaultify.web.dto.CipherDto;
import com.pixelpunch.vaultify.web.dto.PasswordDto;
import com.pixelpunch.vaultify.web.dto.UserDto;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class UserMapperTest {

    @Test
    void testUserToDto() {
        // Arrange
        User user = new User();
        user.setId(1L);
        user.setEmail("test@example.com");
        user.setEmailVerified(true);
        user.setEmailVerificationCode("verificationCode");
        user.setEmailVerificationCodeExpiresAt(new Date());
        user.setPassword("password");
        user.setPrivateKey("privateKey");
        user.setPublicKey("publicKey");
        user.setPasswordHint("passwordHint");
        user.setTwoFactorEnabled(true);
        user.setTwoFactorVerificationCode("twoFactorCode");
        user.setCreated(new Date());
        user.setLastPasswordChange(new Date());

        Cipher cipher = new Cipher();
        cipher.setId(1L);
        cipher.setData("cipherData");
        cipher.setFavorite(true);
        cipher.setCreated(new Date());
        cipher.setLastModified(new Date());
        user.setCiphers(Collections.singletonList(cipher));

        Password password = new Password();
        password.setId(1L);
        password.setPasswords("TestPassword");
        password.setLength(8);
        password.setIncludeUppercases(true);
        password.setIncludeNumbers(true);
        password.setIncludeSpecials(false);
        password.setGeneratedTime(new Date());
        user.setPasswords(Collections.singletonList(password));

        // Act
        UserDto userDto = UserMapper.userToDTO(user);

        // Assert
        assertNotNull(userDto);
        assertEquals(user.getId(), userDto.getId());
        assertEquals(user.getEmail(), userDto.getEmail());
        assertEquals(user.isEmailVerified(), userDto.isEmailVerified());
        assertEquals(user.getEmailVerificationCode(), userDto.getEmailVerificationCode());
        assertEquals(user.getEmailVerificationCodeExpiresAt(), userDto.getEmailVerificationCodeExpiresAt());
        assertEquals(user.getPassword(), userDto.getPassword());
        assertEquals(user.getPrivateKey(), userDto.getPrivateKey());
        assertEquals(user.getPublicKey(), userDto.getPublicKey());
        assertEquals(user.getPasswordHint(), userDto.getPasswordHint());
        assertEquals(user.isTwoFactorEnabled(), userDto.isTwoFactorEnabled());
        assertEquals(user.getTwoFactorVerificationCode(), userDto.getTwoFactorVerificationCode());
        assertEquals(user.getCreated(), userDto.getCreated());
        assertEquals(user.getLastPasswordChange(), userDto.getLastPasswordChange());

        List<CipherDto> ciphers = userDto.getCiphers();
        assertNotNull(ciphers);
        assertEquals(1, ciphers.size());
        assertEquals(cipher.getId(), ciphers.get(0).getId());
        assertEquals(cipher.getData(), ciphers.get(0).getData());
        assertEquals(cipher.isFavorite(), ciphers.get(0).isFavorite());
        assertEquals(cipher.getCreated(), ciphers.get(0).getCreated());
        assertEquals(cipher.getLastModified(), ciphers.get(0).getLastModified());

        List<PasswordDto> passwords = userDto.getPasswords();
        assertNotNull(passwords);
        assertEquals(1, passwords.size());
        assertEquals(password.getId(), passwords.get(0).getId());
        assertEquals(password.getPasswords(), passwords.get(0).getPassword());
        assertEquals(password.getLength(), passwords.get(0).getLength());
        assertEquals(password.isIncludeUppercases(), passwords.get(0).isIncludeUppercase());
        assertEquals(password.isIncludeNumbers(), passwords.get(0).isIncludeNumbers());
        assertEquals(password.isIncludeSpecials(), passwords.get(0).isIncludeSpecial());
        assertEquals(password.getGeneratedTime(), passwords.get(0).getGeneratedTime());
    }
}
