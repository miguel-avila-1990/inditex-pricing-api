package com.inditex.pricing.infrastructure.out.persistence.mapper;

import com.inditex.pricing.domain.model.Brand;
import com.inditex.pricing.domain.model.Price;
import com.inditex.pricing.domain.model.Product;
import com.inditex.pricing.infrastructure.out.persistence.entity.BrandEntity;
import com.inditex.pricing.infrastructure.out.persistence.entity.PriceEntity;
import com.inditex.pricing.infrastructure.out.persistence.entity.ProductEntity;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class PriceMapperTest {

    @Test
    void shouldMapPriceEntityToDomain() {
        // Arrange
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(35455);
        productEntity.setName("Camiseta Básica Blanca");
        productEntity.setCategory("Ropa");
        productEntity.setSize("M");
        productEntity.setGender("Unisex");
        productEntity.setColor("Blanca");
        productEntity.setCollection("Primavera-Verano 2020");

        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setId(1);
        brandEntity.setName("ZARA");
        brandEntity.setCountry("España");
        brandEntity.setDescription("Marca líder en moda");
        brandEntity.setActive(true);

        PriceEntity priceEntity = new PriceEntity();
        priceEntity.setProduct(productEntity);
        priceEntity.setBrand(brandEntity);
        priceEntity.setPriceList(1);
        priceEntity.setPriority(0);
        priceEntity.setStartDate(LocalDateTime.of(2020, 6, 14, 0, 0));
        priceEntity.setEndDate(LocalDateTime.of(2020, 12, 31, 23, 59));
        priceEntity.setPrice(new BigDecimal("35.50"));
        priceEntity.setCurrency("EUR");

        // Act
        Price price = PriceMapper.toDomain(priceEntity);

        // Assert
        assertAll(
            () -> assertEquals(35455, price.product().id()),
            () -> assertEquals(1, price.brand().id()),
            () -> assertEquals(1, price.priceList()),
            () -> assertEquals(0, price.priority()),
            () -> assertEquals(LocalDateTime.of(2020, 6, 14, 0, 0), price.startDate()),
            () -> assertEquals(LocalDateTime.of(2020, 12, 31, 23, 59), price.endDate()),
            () -> assertEquals(new BigDecimal("35.50"), price.price()),
            () -> assertEquals("EUR", price.currency())
        );
    }

    @Test
    void shouldMapPriceDomainToEntity() {
        // Arrange
        Product product = new Product(35455, "Camiseta Básica Blanca", "Ropa", "M", "Unisex", "Blanca", "Primavera-Verano 2020");
        Brand brand = new Brand(1, "ZARA", "España", "Marca líder en moda", true);

        Price price = new Price(
            product,
            brand,
            1,
            LocalDateTime.of(2020, 6, 14, 0, 0),
            LocalDateTime.of(2020, 12, 31, 23, 59),
            0,
            new BigDecimal("35.50"),
            "EUR"
        );

        // Act
        PriceEntity priceEntity = PriceMapper.toEntity(price);

        // Assert
        assertAll(
            () -> assertEquals(35455, priceEntity.getProduct().getId()),
            () -> assertEquals(1, priceEntity.getBrand().getId()),
            () -> assertEquals(1, priceEntity.getPriceList()),
            () -> assertEquals(0, priceEntity.getPriority()),
            () -> assertEquals(LocalDateTime.of(2020, 6, 14, 0, 0), priceEntity.getStartDate()),
            () -> assertEquals(LocalDateTime.of(2020, 12, 31, 23, 59), priceEntity.getEndDate()),
            () -> assertEquals(new BigDecimal("35.50"), priceEntity.getPrice()),
            () -> assertEquals("EUR", priceEntity.getCurrency())
        );
    }
}
