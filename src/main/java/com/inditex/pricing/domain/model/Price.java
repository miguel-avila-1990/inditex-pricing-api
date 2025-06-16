package com.inditex.pricing.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Representa una tarifa de precio para un producto de una marca en un rango de fechas.
 *
 * @param product     Producto al que aplica
 * @param brand       Marca que lo oferta
 * @param priceList   ID de la tarifa
 * @param startDate   Fecha/hora inicio de validez
 * @param endDate     Fecha/hora fin de validez
 * @param priority    Prioridad frente a otras tarifas
 * @param price       Precio final aplicado
 * @param currency    Moneda ISO
 */
public record Price(
    Product product,
    Brand brand,
    Integer priceList,
    LocalDateTime startDate,
    LocalDateTime endDate,
    Integer priority,
    BigDecimal price,
    String currency
) {}
