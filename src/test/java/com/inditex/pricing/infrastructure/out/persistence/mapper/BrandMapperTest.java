package com.inditex.pricing.infrastructure.out.persistence.mapper;

import com.inditex.pricing.domain.model.Brand;
import com.inditex.pricing.infrastructure.out.persistence.entity.BrandEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BrandMapperTest {

	@Test
	void shouldMapBrandEntityToDomain() {
	    // Arrange
	    BrandEntity entity = new BrandEntity();
	    entity.setId(1);
	    entity.setName("ZARA");
	    entity.setCountry("España");
	    entity.setDescription("Marca líder en moda");
	    entity.setActive(true);

	    // Act
	    Brand brand = BrandMapper.toDomain(entity);

	    // Assert
	    assertAll(
	        () -> assertEquals(1, brand.id()),
	        () -> assertEquals("ZARA", brand.name()),
	        () -> assertEquals("España", brand.country()),
	        () -> assertEquals("Marca líder en moda", brand.description()),
	        () -> assertTrue(brand.active())
	    );
	}

	@Test
	void shouldMapBrandDomainToEntity() {
	    // Arrange
	    Brand brand = new Brand(1, "ZARA", "España", "Marca líder en moda", true);

	    // Act
	    BrandEntity entity = BrandMapper.toEntity(brand);

	    // Assert
	    assertAll(
	        () -> assertEquals(1, entity.getId()),
	        () -> assertEquals("ZARA", entity.getName()),
	        () -> assertEquals("España", entity.getCountry()),
	        () -> assertEquals("Marca líder en moda", entity.getDescription()),
	        () -> assertTrue(entity.isActive())
	    );
	}

}
