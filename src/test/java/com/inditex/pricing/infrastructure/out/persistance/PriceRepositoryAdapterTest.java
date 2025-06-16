package com.inditex.pricing.infrastructure.out.persistance;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.inditex.pricing.domain.model.Brand;
import com.inditex.pricing.domain.model.Product;
import com.inditex.pricing.infrastructure.out.persistence.entity.BrandEntity;
import com.inditex.pricing.infrastructure.out.persistence.entity.PriceEntity;
import com.inditex.pricing.infrastructure.out.persistence.entity.ProductEntity;
import com.inditex.pricing.infrastructure.out.persistence.repository.JpaPriceRepository;
import com.inditex.pricing.infrastructure.out.persistence.repository.PriceRepositoryAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;

public class PriceRepositoryAdapterTest {

    private JpaPriceRepository jpaPriceRepository;
    private PriceRepositoryAdapter adapter;

    @BeforeEach
    void setUp() {
        jpaPriceRepository = mock(JpaPriceRepository.class);
        adapter = new PriceRepositoryAdapter(jpaPriceRepository);
    }

    @Test
    void shouldReturnMappedPriceFromJpaRepository() {
        // Datos de prueba
        Integer productId = 35455;
        Integer brandId = 1;
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 16, 0);

        // Entities
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
        priceEntity.setCurr("EUR");

        when(jpaPriceRepository.findPriceByDateProductAndBrand(
                eq(productId), eq(brandId), eq(date), eq(PageRequest.of(0, 1))))
            .thenReturn(List.of(priceEntity));

        // Ejecutar
        Optional<com.inditex.pricing.domain.model.Price> result =
                adapter.findPrice(date, productId, brandId);

        // Verificar
        assertThat(result).isPresent();
        assertThat(result.get().price()).isEqualByComparingTo("25.45");
        assertThat(result.get().brand().id()).isEqualTo(1);
        assertThat(result.get().product().id()).isEqualTo(35455);
        assertThat(result.get().currency()).isEqualTo("EUR");
    }
}
