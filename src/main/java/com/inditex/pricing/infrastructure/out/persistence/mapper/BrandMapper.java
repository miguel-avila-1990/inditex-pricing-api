package com.inditex.pricing.infrastructure.out.persistence.mapper;

import com.inditex.pricing.domain.model.Brand;
import com.inditex.pricing.infrastructure.out.persistence.entity.BrandEntity;

/**
 * Mapper para convertir entre {@link BrandEntity} (infraestructura) y {@link Brand} (dominio).
 * Este mapeo permite desacoplar la lógica de negocio de la persistencia.
 */
public class BrandMapper {

    /**
     * Convierte una entidad JPA a un objeto del modelo de dominio.
     *
     * @param entity la entidad JPA
     * @return Brand objeto del dominio
     */
    public static Brand toDomain(BrandEntity entity) {
        return new Brand(
            entity.getId(),
            entity.getName(),
            entity.getCountry(),
            entity.getDescription(),
            entity.isActive()
        );
    }

    /**
     * Convierte un objeto del dominio a su correspondiente entidad JPA.
     *
     * @param brand objeto del dominio
     * @return entidad JPA correspondiente
     */
    public static BrandEntity toEntity(Brand brand) {
        BrandEntity entity = new BrandEntity();
        entity.setId(brand.id());
        entity.setName(brand.name());
        entity.setCountry(brand.country());
        entity.setDescription(brand.description());
        entity.setActive(brand.active());
        return entity;
    }
}
