package com.inditex.pricing.domain.ports.in;

import java.time.LocalDateTime;

import com.inditex.pricing.domain.model.Price;

/**
 * Caso de uso para obtener el precio aplicable a un producto en una fecha y cadena concretas.
 * <p>
 * Aplica las reglas de negocio definidas para:
 * <ul>
 *   <li>Buscar precios válidos para la fecha dada (rango entre startDate y endDate)</li>
 *   <li>Filtrar por producto y cadena</li>
 *   <li>Seleccionar el precio con mayor prioridad si hay solapamientos</li>
 * </ul>
 *
 * @param applicationDate Fecha y hora de la consulta (formato ISO 8601)
 * @param productId       Identificador del producto
 * @param brandId         Identificador de la cadena (1 = ZARA)
 * @return Precio aplicable con la tarifa correspondiente
 * @throws PriceNotFoundException si no hay ningún precio aplicable
 */
public interface GetApplicablePriceUseCase {
    Price getApplicablePrice(LocalDateTime applicationDate, Integer productId, Integer brandId);
}
