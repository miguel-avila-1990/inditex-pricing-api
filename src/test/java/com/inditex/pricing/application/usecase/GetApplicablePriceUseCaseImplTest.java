package com.inditex.pricing.application.usecase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.inditex.pricing.domain.exceptions.InvalidInputException;
import com.inditex.pricing.domain.exceptions.PriceNotFoundException;
import com.inditex.pricing.domain.model.Brand;
import com.inditex.pricing.domain.model.Price;
import com.inditex.pricing.domain.model.Product;
import com.inditex.pricing.domain.ports.out.PriceRepositoryPort;

public class GetApplicablePriceUseCaseImplTest {

    private PriceRepositoryPort priceRepository;
    private GetApplicablePriceUseCaseImpl useCase;

    @BeforeEach
    void setUp() {
        priceRepository = mock(PriceRepositoryPort.class);
        useCase = new GetApplicablePriceUseCaseImpl(priceRepository);
    }

    @Test
    void shouldReturnPriceWhenExists() {
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 10, 0);
        Integer productId = 35455;
        Integer brandId = 1;

        Product product = new Product(productId, "Camiseta", "camiseta", "M", "unisex", "blanco", "primavera-verano 2020");
        Brand brand = new Brand(brandId, "ZARA", "España", "Marca líder", true);

        Price price = new Price(product, brand, 1, date, date.plusDays(10), 0, new BigDecimal("35.50"), "EUR");

        when(priceRepository.findPrice(date, productId, brandId)).thenReturn(Optional.of(price));

        Price result = useCase.getApplicablePrice(date, productId, brandId);

        assertThat(result).isEqualTo(price);
    }

    @Test
    void shouldThrowExceptionWhenNoPriceFound() {
        LocalDateTime date = LocalDateTime.now();
        when(priceRepository.findPrice(date, 35455, 1)).thenReturn(Optional.empty());

        assertThrows(PriceNotFoundException.class, () ->
                useCase.getApplicablePrice(date, 35455, 1));
    }

    @Test
    void shouldThrowInvalidInputExceptionWhenParamsAreNull() {
        assertThrows(InvalidInputException.class, () ->
                useCase.getApplicablePrice(null, 35455, 1));

        assertThrows(InvalidInputException.class, () ->
                useCase.getApplicablePrice(LocalDateTime.now(), null, 1));

        assertThrows(InvalidInputException.class, () ->
                useCase.getApplicablePrice(LocalDateTime.now(), 35455, null));
    }
}
