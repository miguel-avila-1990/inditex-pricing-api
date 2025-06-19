package com.inditex.pricing.infrastructure.in.rest.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.inditex.pricing.domain.model.Brand;
import com.inditex.pricing.domain.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.inditex.pricing.domain.model.Price;
import com.inditex.pricing.domain.ports.in.GetApplicablePriceUseCase;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(controllers = PriceController.class)
class PriceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetApplicablePriceUseCase getApplicablePriceUseCase;

    private Price price;

    @BeforeEach
    void setUp() {
        Product product = new Product(35455, "Camiseta básica blanca", "camiseta", "M", "unisex", "blanco", "primavera-verano 2020");
        Brand brand = new Brand(1, "ZARA", "España", "Marca líder", true);

        price = new Price(
            product,
            brand,
            2,
            LocalDateTime.of(2020, 6, 14, 15, 0),
            LocalDateTime.of(2020, 6, 14, 18, 30),
            1,
            new BigDecimal("25.45"),
            "EUR"
        );
    }

    @Test
    @DisplayName("Test 1: should return price at 10:00 on June 14")
    void shouldReturnPriceAt10AM_June14() throws Exception {
        LocalDateTime testDate = LocalDateTime.of(2020, 6, 14, 10, 0);
        Price testPrice = new Price(
            price.product(),
            price.brand(),
            1,
            LocalDateTime.of(2020, 6, 14, 0, 0),
            LocalDateTime.of(2020, 12, 31, 23, 59, 59),
            0,
            new BigDecimal("35.50"),
            "EUR"
        );

        when(getApplicablePriceUseCase.getApplicablePrice(testDate, 35455, 1))
            .thenReturn(testPrice);

        mockMvc.perform(MockMvcRequestBuilders.get("/prices")
                .param("consultationDate", testDate.toString())
                .param("productId", "35455")
                .param("brandId", "1")
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.productId").value(35455))
            .andExpect(jsonPath("$.brandId").value(1))
            .andExpect(jsonPath("$.priceList").value(1))
            .andExpect(jsonPath("$.price").value(35.50))
            .andExpect(jsonPath("$.currency").value("EUR"));
    }

    @Test
    @DisplayName("Test 2: should return price at 16:00 on June 14")
    void shouldReturnPriceAt16PM_June14() throws Exception {
        LocalDateTime testDate = LocalDateTime.of(2020, 6, 14, 16, 0);

        when(getApplicablePriceUseCase.getApplicablePrice(testDate, 35455, 1))
            .thenReturn(price);

        mockMvc.perform(MockMvcRequestBuilders.get("/prices")
                .param("consultationDate", testDate.toString())
                .param("productId", "35455")
                .param("brandId", "1")
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.productId").value(35455))
            .andExpect(jsonPath("$.brandId").value(1))
            .andExpect(jsonPath("$.priceList").value(2))
            .andExpect(jsonPath("$.price").value(25.45))
            .andExpect(jsonPath("$.currency").value("EUR"));
    }

    @Test
    @DisplayName("Test 3: should return price at 21:00 on June 14")
    void shouldReturnPriceAt21PM_June14() throws Exception {
        LocalDateTime testDate = LocalDateTime.of(2020, 6, 14, 21, 0);
        Price testPrice = new Price(
            price.product(),
            price.brand(),
            1,
            LocalDateTime.of(2020, 6, 14, 0, 0),
            LocalDateTime.of(2020, 12, 31, 23, 59, 59),
            0,
            new BigDecimal("35.50"),
            "EUR"
        );

        when(getApplicablePriceUseCase.getApplicablePrice(testDate, 35455, 1))
            .thenReturn(testPrice);

        mockMvc.perform(MockMvcRequestBuilders.get("/prices")
                .param("consultationDate", testDate.toString())
                .param("productId", "35455")
                .param("brandId", "1")
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.productId").value(35455))
            .andExpect(jsonPath("$.brandId").value(1))
            .andExpect(jsonPath("$.priceList").value(1))
            .andExpect(jsonPath("$.price").value(35.50))
            .andExpect(jsonPath("$.currency").value("EUR"));
    }

    @Test
    @DisplayName("Test 4: should return price at 10:00 on June 15")
    void shouldReturnPriceAt10AM_June15() throws Exception {
        LocalDateTime testDate = LocalDateTime.of(2020, 6, 15, 10, 0);
        Price testPrice = new Price(
            price.product(),
            price.brand(),
            3,
            LocalDateTime.of(2020, 6, 15, 0, 0),
            LocalDateTime.of(2020, 6, 15, 11, 0),
            1,
            new BigDecimal("30.50"),
            "EUR"
        );

        when(getApplicablePriceUseCase.getApplicablePrice(testDate, 35455, 1))
            .thenReturn(testPrice);

        mockMvc.perform(MockMvcRequestBuilders.get("/prices")
                .param("consultationDate", testDate.toString())
                .param("productId", "35455")
                .param("brandId", "1")
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.productId").value(35455))
            .andExpect(jsonPath("$.brandId").value(1))
            .andExpect(jsonPath("$.priceList").value(3))
            .andExpect(jsonPath("$.price").value(30.50))
            .andExpect(jsonPath("$.currency").value("EUR"));
    }

    @Test
    @DisplayName("Test 5: should return price at 21:00 on June 16")
    void shouldReturnPriceAt21PM_June16() throws Exception {
        LocalDateTime testDate = LocalDateTime.of(2020, 6, 16, 21, 0);
        Price testPrice = new Price(
            price.product(),
            price.brand(),
            4,
            LocalDateTime.of(2020, 6, 15, 16, 0),
            LocalDateTime.of(2020, 12, 31, 23, 59, 59),
            1,
            new BigDecimal("38.95"),
            "EUR"
        );

        when(getApplicablePriceUseCase.getApplicablePrice(testDate, 35455, 1))
            .thenReturn(testPrice);

        mockMvc.perform(MockMvcRequestBuilders.get("/prices")
                .param("consultationDate", testDate.toString())
                .param("productId", "35455")
                .param("brandId", "1")
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.productId").value(35455))
            .andExpect(jsonPath("$.brandId").value(1))
            .andExpect(jsonPath("$.priceList").value(4))
            .andExpect(jsonPath("$.price").value(38.95))
            .andExpect(jsonPath("$.currency").value("EUR"));
    }
}
