package com.inditex.pricing.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.inditex.pricing.application.usecase.GetApplicablePriceUseCaseImpl;
import com.inditex.pricing.domain.model.Price;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class GetApplicablePriceUseCaseIntegrationTest {

    @Autowired
    private GetApplicablePriceUseCaseImpl getApplicablePriceUseCaseImpl;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testGetApplicablePrice() {
        // Definir fecha de consulta y parámetros de entrada
        LocalDateTime testDate = LocalDateTime.of(2020, 6, 14, 10, 0);
        int productId = 35455;
        int brandId = 1;

        // Llamar al caso de uso
        Price price = getApplicablePriceUseCaseImpl.getApplicablePrice(testDate, productId, brandId);

        // Verificar que el resultado es el esperado
        assertEquals(35455, price.product().id());
        assertEquals("ZARA", price.brand().name());
        assertEquals(new BigDecimal("35.50"), price.price());
        assertEquals("EUR", price.currency());
    }
}
