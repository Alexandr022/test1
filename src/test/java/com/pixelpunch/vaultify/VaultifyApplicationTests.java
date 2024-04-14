package com.pixelpunch.vaultify;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.boot.SpringApplication;

class VaultifyApplicationTests {

    @Test
    void testMain() {
        // Проверяем, что SpringApplication.run вызывается с правильными аргументами
        try (MockedStatic<SpringApplication> springApplicationMockedStatic = Mockito.mockStatic(SpringApplication.class)) {
            VaultifyApplication.main(new String[]{});
            springApplicationMockedStatic.verify(() -> SpringApplication.run(VaultifyApplication.class, new String[]{}));
        }
    }

    @Test
    void testMainWithCustomArgs() {
        // Проверяем, что SpringApplication.run вызывается с правильными аргументами при наличии пользовательских аргументов
        String[] args = {"--customArg=test"};
        try (MockedStatic<SpringApplication> springApplicationMockedStatic = Mockito.mockStatic(SpringApplication.class)) {
            VaultifyApplication.main(args);
            springApplicationMockedStatic.verify(() -> SpringApplication.run(VaultifyApplication.class, args));
        }
    }
}
