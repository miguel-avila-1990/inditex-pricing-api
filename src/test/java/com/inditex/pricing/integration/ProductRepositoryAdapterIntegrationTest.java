package com.inditex.pricing.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.inditex.pricing.infrastructure.out.persistence.entity.ProductEntity;
import com.inditex.pricing.infrastructure.out.persistence.repository.JpaProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class ProductRepositoryAdapterIntegrationTest {

    @Autowired
    private JpaProductRepository jpaProductRepository;

    @Test
    void testSaveAndRetrieveProduct() {
        // Crear un nuevo objeto ProductEntity
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(3);
        productEntity.setName("Camiseta Básica");

        // Guardar la entidad en la base de datos
        ProductEntity savedProduct = jpaProductRepository.save(productEntity);

        // Verificar que la entidad se guardó correctamente
        assertEquals(3, savedProduct.getId());

        // Recuperar la entidad desde la base de datos
        ProductEntity retrievedProduct = jpaProductRepository.findById(savedProduct.getId()).orElse(null);

        // Verificar que la entidad fue recuperada correctamente
        assertEquals(3, retrievedProduct.getId());
    }
}
