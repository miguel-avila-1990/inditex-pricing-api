package com.inditex.pricing.domain.ports.out;

import com.inditex.pricing.infrastructure.out.persistence.entity.ProductEntity;

/**
 * Puerto de salida para la persistencia de productos (Product).
 */
public interface ProductRepositoryPort {

    /**
     * Guarda una entidad de producto.
     *
     * @param productEntity entidad de producto a guardar
     * @return la entidad de producto persistida
     */
    ProductEntity save(ProductEntity productEntity);
}
