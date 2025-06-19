package com.inditex.pricing.infrastructure.out.persistence.mapper;

import com.inditex.pricing.domain.model.Product;
import com.inditex.pricing.infrastructure.out.persistence.entity.ProductEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductMapperTest {

    @Test
    void shouldMapProductEntityToDomain() {
        // Arrange
        ProductEntity entity = new ProductEntity();
        entity.setId(35455);
        entity.setName("Camiseta Básica Blanca");
        entity.setCategory("Ropa");
        entity.setSize("M");
        entity.setGender("Unisex");
        entity.setColor("Blanca");
        entity.setCollection("Primavera-Verano 2020");

        // Act
        Product product = ProductMapper.toDomain(entity);

        // Assert
        assertAll(
            () -> assertEquals(35455, product.id()),
            () -> assertEquals("Camiseta Básica Blanca", product.name()),
            () -> assertEquals("Ropa", product.category()),
            () -> assertEquals("M", product.size()),
            () -> assertEquals("Unisex", product.gender()),
            () -> assertEquals("Blanca", product.color()),
            () -> assertEquals("Primavera-Verano 2020", product.collection())
        );
    }

    @Test
    void shouldMapProductDomainToEntity() {
        // Arrange
        Product product = new Product(35455, "Camiseta Básica Blanca", "Ropa", "M", "Unisex", "Blanca", "Primavera-Verano 2020");

        // Act
        ProductEntity entity = ProductMapper.toEntity(product);

        // Assert
        assertAll(
            () -> assertEquals(35455, entity.getId()),
            () -> assertEquals("Camiseta Básica Blanca", entity.getName()),
            () -> assertEquals("Ropa", entity.getCategory()),
            () -> assertEquals("M", entity.getSize()),
            () -> assertEquals("Unisex", entity.getGender()),
            () -> assertEquals("Blanca", entity.getColor()),
            () -> assertEquals("Primavera-Verano 2020", entity.getCollection())
        );
    }
}
