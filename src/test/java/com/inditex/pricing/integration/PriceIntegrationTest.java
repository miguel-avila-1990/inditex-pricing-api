package com.inditex.pricing.integration;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import com.inditex.pricing.infrastructure.in.rest.dto.PriceResponseDto;

/**
 * Prueba de integración completa:
 * 
 * Se valida el comportamiento completo de la aplicación (controller + servicio + repositorio + BBDD en memoria H2)
 * con los 5 escenarios definidos en el enunciado y un escenario de error (petición sin precio encontrado).
 * 
 * Estas pruebas garantizan que la API REST devuelve correctamente los precios en función de la fecha,
 * producto y cadena (brand), y que maneja correctamente los casos de no encontrado (404).
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class PriceIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @DisplayName("Scenario 1: 14/06/2020 10:00")
    void shouldReturnCorrectPriceForScenario1() {
        var response = restTemplate.getForEntity(
                "/prices?consultationDate=2020-06-14T10:00:00&productId=35455&brandId=1",
                PriceResponseDto.class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().productId()).isEqualTo(35455);
        assertThat(response.getBody().brandId()).isEqualTo(1);
        assertThat(response.getBody().priceList()).isEqualTo(1);
        assertThat(response.getBody().price()).isEqualByComparingTo(new BigDecimal("35.50"));
        assertThat(response.getBody().currency()).isEqualTo("EUR");
    }

    @Test
    @DisplayName("Scenario 2: 14/06/2020 16:00")
    void shouldReturnCorrectPriceForScenario2() {
        var response = restTemplate.getForEntity(
                "/prices?consultationDate=2020-06-14T16:00:00&productId=35455&brandId=1",
                PriceResponseDto.class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().productId()).isEqualTo(35455);
        assertThat(response.getBody().brandId()).isEqualTo(1);
        assertThat(response.getBody().priceList()).isEqualTo(2);
        assertThat(response.getBody().price()).isEqualByComparingTo(new BigDecimal("25.45"));
        assertThat(response.getBody().currency()).isEqualTo("EUR");
    }

    @Test
    @DisplayName("Scenario 3: 14/06/2020 21:00")
    void shouldReturnCorrectPriceForScenario3() {
        var response = restTemplate.getForEntity(
                "/prices?consultationDate=2020-06-14T21:00:00&productId=35455&brandId=1",
                PriceResponseDto.class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().productId()).isEqualTo(35455);
        assertThat(response.getBody().brandId()).isEqualTo(1);
        assertThat(response.getBody().priceList()).isEqualTo(1);
        assertThat(response.getBody().price()).isEqualByComparingTo(new BigDecimal("35.50"));
        assertThat(response.getBody().currency()).isEqualTo("EUR");
    }

    @Test
    @DisplayName("Scenario 4: 15/06/2020 10:00")
    void shouldReturnCorrectPriceForScenario4() {
        var response = restTemplate.getForEntity(
                "/prices?consultationDate=2020-06-15T10:00:00&productId=35455&brandId=1",
                PriceResponseDto.class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().productId()).isEqualTo(35455);
        assertThat(response.getBody().brandId()).isEqualTo(1);
        assertThat(response.getBody().priceList()).isEqualTo(3);
        assertThat(response.getBody().price()).isEqualByComparingTo(new BigDecimal("30.50"));
        assertThat(response.getBody().currency()).isEqualTo("EUR");
    }

    @Test
    @DisplayName("Scenario 5: 16/06/2020 21:00")
    void shouldReturnCorrectPriceForScenario5() {
        var response = restTemplate.getForEntity(
                "/prices?consultationDate=2020-06-16T21:00:00&productId=35455&brandId=1",
                PriceResponseDto.class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().productId()).isEqualTo(35455);
        assertThat(response.getBody().brandId()).isEqualTo(1);
        assertThat(response.getBody().priceList()).isEqualTo(4);
        assertThat(response.getBody().price()).isEqualByComparingTo(new BigDecimal("38.95"));
        assertThat(response.getBody().currency()).isEqualTo("EUR");
    }

    @Test
    @DisplayName("Scenario 6: No price found - should return 404")
    void shouldReturn404WhenNoPriceFound() {
        var response = restTemplate.getForEntity(
                "/prices?consultationDate=2025-01-01T00:00:00&productId=99999&brandId=1",
                String.class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}
