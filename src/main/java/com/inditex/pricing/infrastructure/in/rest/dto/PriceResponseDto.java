package com.inditex.pricing.infrastructure.in.rest.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.inditex.pricing.domain.model.Price;

/**
 * DTO expuesto como respuesta del endpoint de precios.
 *
 * @param productId  ID del producto consultado
 * @param brandId    ID de la cadena (brand)
 * @param priceList  Tarifa aplicada
 * @param startDate  Fecha de inicio de validez del precio
 * @param endDate    Fecha de fin de validez del precio
 * @param price      Precio final aplicado
 * @param currency   Moneda del precio
 */
public record PriceResponseDto(
        Integer productId,
        Integer brandId,
        Integer priceList,
        LocalDateTime startDate,
        LocalDateTime endDate,
        BigDecimal price,
        String currency
) {
    /**
     * Constructor que transforma un objeto del dominio {@link Price} en un DTO expuesto por la API REST.
     *
     * @param price objeto del dominio
     */
    public PriceResponseDto(Price price) {
        this(
            price.product().id(),
            price.brand().id(),
            price.priceList(),
            price.startDate(),
            price.endDate(),
            price.price(),
            price.currency()
        );
    }
}
