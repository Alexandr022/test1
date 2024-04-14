package com.pixelpunch.vaultify.model;

import com.pixelpunch.vaultify.core.model.Password;
import com.pixelpunch.vaultify.core.model.User;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PasswordTest {

    @Test
    void testConstructorAndGetters() {
        Long id = 1L;
        int length = 10;
        boolean includeUppercases = true;
        boolean includeNumbers = true;
        boolean includeSpecials = true;
        Date generatedTime = new Date();
        String passwords = "password123";
        User owner = new User("test@example.com");

        Password password = new Password(id, length, includeUppercases, includeNumbers, includeSpecials, generatedTime, passwords, owner);

        password.setId(id);
        password.setLength(length);
        password.setIncludeUppercases(includeUppercases);
        password.setIncludeNumbers(includeNumbers);
        password.setIncludeSpecials(includeSpecials);
        password.setGeneratedTime(generatedTime);
        password.setPasswords(passwords);
        password.setOwner(owner);

        assertNotNull(password);
        assertEquals(id, password.getId());
        assertEquals(length, password.getLength());
        assertEquals(includeUppercases, password.isIncludeUppercases());
        assertEquals(includeNumbers, password.isIncludeNumbers());
        assertEquals(includeSpecials, password.isIncludeSpecials());
        assertEquals(generatedTime, password.getGeneratedTime());
        assertEquals(passwords, password.getPasswords());
        assertEquals(owner, password.getOwner());
    }
}
