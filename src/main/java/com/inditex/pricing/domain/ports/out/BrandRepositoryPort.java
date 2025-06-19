package com.inditex.pricing.domain.ports.out;

import com.inditex.pricing.infrastructure.out.persistence.entity.BrandEntity;

/**
 * Puerto de salida para la persistencia de marcas (Brand).
 */
public interface BrandRepositoryPort {

    /**
     * Guarda una entidad de marca.
     *
     * @param brandEntity entidad de marca a guardar
     * @return la entidad de marca persistida
     */
    BrandEntity save(BrandEntity brandEntity);
}
