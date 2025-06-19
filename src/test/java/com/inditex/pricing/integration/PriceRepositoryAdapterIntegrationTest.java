package com.inditex.pricing.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.inditex.pricing.infrastructure.out.persistence.entity.BrandEntity;
import com.inditex.pricing.infrastructure.out.persistence.entity.PriceEntity;
import com.inditex.pricing.infrastructure.out.persistence.entity.ProductEntity;
import com.inditex.pricing.infrastructure.out.persistence.repository.JpaBrandRepository;
import com.inditex.pricing.infrastructure.out.persistence.repository.JpaPriceRepository;
import com.inditex.pricing.infrastructure.out.persistence.repository.JpaProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class PriceRepositoryAdapterIntegrationTest {

    @Autowired
    private JpaPriceRepository jpaPriceRepository;

    @Autowired
    private JpaBrandRepository jpaBrandRepository;

    @Autowired
    private JpaProductRepository jpaProductRepository;

    @Test
    void testSaveAndRetrievePrice() {
        // Crear un nuevo objeto ProductEntity (producto)
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName("Camiseta Básica");
        productEntity.setId(2);
        // Guardar el producto en la base de datos
        ProductEntity savedProduct = jpaProductRepository.save(productEntity);

        // Crear un nuevo objeto BrandEntity (marca)
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setId(3);
        brandEntity.setName("ZARAMADRID");
        // Guardar la marca en la base de datos
        BrandEntity savedBrand = jpaBrandRepository.save(brandEntity);

        // Crear un nuevo objeto PriceEntity (precio), asociando el producto y la marca
        PriceEntity priceEntity = new PriceEntity();
        priceEntity.setProduct(savedProduct);
        priceEntity.setBrand(savedBrand);
        priceEntity.setPrice(new BigDecimal("35.50"));
        priceEntity.setStartDate(LocalDateTime.of(2020, 6, 14, 0, 0));
        priceEntity.setEndDate(LocalDateTime.of(2020, 12, 31, 23, 59));

        // Guardar el precio en la base de datos
        PriceEntity savedPrice = jpaPriceRepository.save(priceEntity);

        // Verificar que la entidad Price se guardó correctamente
        assertEquals(new BigDecimal("35.50"), savedPrice.getPrice());

        // Recuperar la entidad desde la base de datos
        PriceEntity retrievedPrice = jpaPriceRepository.findById(savedPrice.getId()).orElse(null);

        // Verificar que la entidad fue recuperada correctamente
        assertEquals(new BigDecimal("35.50"), retrievedPrice.getPrice());
        assertEquals(3, retrievedPrice.getBrand().getId());
        assertEquals(2, retrievedPrice.getProduct().getId());
    }
}
