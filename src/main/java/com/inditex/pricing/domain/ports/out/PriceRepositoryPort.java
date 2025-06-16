package com.inditex.pricing.domain.ports.out;

import com.inditex.pricing.domain.model.Price;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Puerto de salida que define el contrato para acceder a los precios desde la infraestructura de persistencia.
 * 
 * @param applicationDate Fecha y hora de aplicación del precio
 * @param productId       Identificador del producto
 * @param brandId         Identificador de la cadena
 * @return Precio aplicable, si existe
 */
public interface PriceRepositoryPort {
    Optional<Price> findPrice(LocalDateTime applicationDate, Integer productId, Integer brandId);
}
