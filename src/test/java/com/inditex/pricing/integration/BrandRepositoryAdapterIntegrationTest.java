package com.inditex.pricing.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.inditex.pricing.infrastructure.out.persistence.entity.BrandEntity;
import com.inditex.pricing.infrastructure.out.persistence.repository.JpaBrandRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class BrandRepositoryAdapterIntegrationTest {

    @Autowired
    private JpaBrandRepository jpaBrandRepository;

    @Test
    void testSaveAndRetrieveBrand() {
        // Crear un nuevo objeto BrandEntity
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setId(2);
        brandEntity.setName("ZARAHOME");

        // Guardar la entidad en la base de datos
        BrandEntity savedBrand = jpaBrandRepository.save(brandEntity);

        // Verificar que la entidad se guardó correctamente
        assertEquals(2, savedBrand.getId());

        // Recuperar la entidad desde la base de datos
        BrandEntity retrievedBrand = jpaBrandRepository.findById(savedBrand.getId()).orElse(null);

        // Verificar que la entidad fue recuperada correctamente
        assertEquals(2, retrievedBrand.getId());
    }
}
