package com.inditex.pricing.infrastructure.out.persistence;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.inditex.pricing.infrastructure.out.persistence.adapter.PriceRepositoryAdapter;
import com.inditex.pricing.infrastructure.out.persistence.entity.BrandEntity;
import com.inditex.pricing.infrastructure.out.persistence.entity.PriceEntity;
import com.inditex.pricing.infrastructure.out.persistence.entity.ProductEntity;
import com.inditex.pricing.infrastructure.out.persistence.repository.JpaPriceRepository;

class PriceRepositoryAdapterTest {

    private JpaPriceRepository jpaPriceRepository;
    private PriceRepositoryAdapter adapter;

    @BeforeEach
    void setUp() {
        jpaPriceRepository = mock(JpaPriceRepository.class);
        adapter = new PriceRepositoryAdapter(jpaPriceRepository);
    }

    @Test
    @DisplayName("Should map and return price from Jpa repository")
    void shouldReturnMappedPriceFromJpaRepository() {
        // Arrange
        Integer productId = 35455;
        Integer brandId = 1;
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 16, 0);

        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setId(brandId);
        brandEntity.setName("ZARA");
        brandEntity.setCountry("España");
        brandEntity.setDescription("Marca líder");
        brandEntity.setActive(true);

        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(productId);
        productEntity.setName("Camiseta básica blanca");
        productEntity.setCategory("camiseta");
        productEntity.setSize("M");
        productEntity.setGender("unisex");
        productEntity.setColor("blanco");
        productEntity.setCollection("primavera-verano 2020");

        PriceEntity priceEntity = new PriceEntity();
        priceEntity.setId(99L);
        priceEntity.setProduct(productEntity);
        priceEntity.setBrand(brandEntity);
        priceEntity.setPriceList(2);
        priceEntity.setStartDate(LocalDateTime.of(2020, 6, 14, 15, 0));
        priceEntity.setEndDate(LocalDateTime.of(2020, 6, 14, 18, 30));
        priceEntity.setPriority(1);
        priceEntity.setPrice(new BigDecimal("25.45"));
        priceEntity.setCurrency("EUR");

        when(jpaPriceRepository.findFirstByProduct_IdAndBrand_IdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
                eq(productId), eq(brandId), eq(date), eq(date)
        )).thenReturn(Optional.of(priceEntity));

        // Act
        Optional<com.inditex.pricing.domain.model.Price> result =
                adapter.findPrice(date, productId, brandId);

        // Assert
        assertThat(result).isPresent();
        assertThat(result.get().price()).isEqualByComparingTo("25.45");
        assertThat(result.get().brand().id()).isEqualTo(1);
        assertThat(result.get().product().id()).isEqualTo(35455);
        assertThat(result.get().currency()).isEqualTo("EUR");
    }
}
