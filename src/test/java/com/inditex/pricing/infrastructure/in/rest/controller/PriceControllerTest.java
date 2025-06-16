package com.inditex.pricing.infrastructure.in.rest.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.inditex.pricing.domain.model.Brand;
import com.inditex.pricing.domain.model.Product;
import org.junit.jupiter.api.BeforeEach;
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
    void shouldReturnPriceResponse() throws Exception {
        when(getApplicablePriceUseCase.getApplicablePrice(
                any(LocalDateTime.class), eq(35455), eq(1)))
            .thenReturn(price);

        mockMvc.perform(MockMvcRequestBuilders.get("/prices")
                        .param("consultationDate", "2020-06-14T15:00:00")
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
}
