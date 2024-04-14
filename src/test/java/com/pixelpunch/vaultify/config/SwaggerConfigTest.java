package com.pixelpunch.vaultify.config;

import com.pixelpunch.vaultify.web.config.SwaggerConfig;
import org.junit.jupiter.api.Test;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SwaggerConfigTest {

    @Test
    void testApi() {
        // Arrange
        SwaggerConfig swaggerConfig = new SwaggerConfig();

        // Act
        Docket docket = swaggerConfig.api();

        // Assert
        assertNotNull(docket);
        assertEquals(DocumentationType.SWAGGER_2, docket.getDocumentationType());
    }
}

