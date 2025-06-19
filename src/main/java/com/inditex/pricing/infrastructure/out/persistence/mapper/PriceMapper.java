package com.inditex.pricing.infrastructure.out.persistence.mapper;

import com.inditex.pricing.domain.model.Price;
import com.inditex.pricing.infrastructure.out.persistence.entity.PriceEntity;

/**
 * Mapper para convertir entre {@link PriceEntity} (infraestructura) y {@link Price} (dominio).
 */
public class PriceMapper {

    /**
     * Convierte una entidad JPA a un objeto del dominio {@link Price}, incluyendo sus referencias.
     *
     * @param entity entidad JPA
     * @return objeto del dominio
     */
    public static Price toDomain(PriceEntity entity) {
        return new Price(
            ProductMapper.toDomain(entity.getProduct()),
            BrandMapper.toDomain(entity.getBrand()),
            entity.getPriceList(),
            entity.getStartDate(),
            entity.getEndDate(),
            entity.getPriority(),
            entity.getPrice(),
            entity.getCurrency()
        );
    }

    /**
     * Convierte un objeto del dominio {@link Price} a una entidad JPA lista para persistencia.
     *
     * @param price objeto del dominio
     * @return entidad JPA correspondiente
     */
    public static PriceEntity toEntity(Price price) {
        PriceEntity entity = new PriceEntity();
        entity.setProduct(ProductMapper.toEntity(price.product()));
        entity.setBrand(BrandMapper.toEntity(price.brand()));
        entity.setPriceList(price.priceList());
        entity.setStartDate(price.startDate());
        entity.setEndDate(price.endDate());
        entity.setPriority(price.priority());
        entity.setPrice(price.price());
        entity.setCurrency(price.currency());
        return entity;
    }
}
