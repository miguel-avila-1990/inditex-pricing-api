package com.inditex.pricing.infrastructure.out.persistence.mapper;

import com.inditex.pricing.domain.model.Product;
import com.inditex.pricing.infrastructure.out.persistence.entity.ProductEntity;

/**
 * Mapper para convertir entre {@link ProductEntity} (infraestructura) y {@link Product} (dominio).
 */
public class ProductMapper {

    /**
     * Convierte una entidad JPA en un objeto del modelo de dominio.
     *
     * @param entity entidad JPA de producto
     * @return objeto del dominio {@link Product}
     */
    public static Product toDomain(ProductEntity entity) {
        return new Product(
            entity.getId(),
            entity.getName(),
            entity.getCategory(),
            entity.getSize(),
            entity.getGender(),
            entity.getColor(),
            entity.getCollection()
        );
    }

    /**
     * Convierte un objeto del dominio en una entidad JPA para persistencia.
     *
     * @param product objeto del dominio
     * @return entidad JPA correspondiente
     */
    public static ProductEntity toEntity(Product product) {
        ProductEntity entity = new ProductEntity();
        entity.setId(product.id());
        entity.setName(product.name());
        entity.setCategory(product.category());
        entity.setSize(product.size());
        entity.setGender(product.gender());
        entity.setColor(product.color());
        entity.setCollection(product.collection());
        return entity;
    }
}
