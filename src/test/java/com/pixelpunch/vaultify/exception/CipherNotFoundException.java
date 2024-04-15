package com.pixelpunch.vaultify.core.exeption;

public class CipherNotFoundException extends RuntimeException {
    public CipherNotFoundException(String message) {
        super(message);
    }
}
