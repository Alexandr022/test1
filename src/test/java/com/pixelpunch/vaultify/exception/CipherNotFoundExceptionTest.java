package com.pixelpunch.vaultify.exception;

import com.pixelpunch.vaultify.core.exeption.CipherNotFoundException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CipherNotFoundExceptionTest {

    @Test
    void testConstructorAndGetMessage() {
        // Arrange
        String errorMessage = "Cipher not found.";

        // Act
        CipherNotFoundException exception = new CipherNotFoundException(errorMessage);

        // Assert
        assertEquals(errorMessage, exception.getMessage());
    }
}

