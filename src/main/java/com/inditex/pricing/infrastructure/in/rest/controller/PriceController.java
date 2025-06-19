package com.inditex.pricing.infrastructure.in.rest.controller;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.inditex.pricing.domain.exceptions.InvalidInputException;
import com.inditex.pricing.domain.model.Price;
import com.inditex.pricing.domain.ports.in.GetApplicablePriceUseCase;
import com.inditex.pricing.infrastructure.in.openapi.api.PricesApi;
import com.inditex.pricing.infrastructure.in.rest.dto.PriceResponseDto;

/**
 * Adaptador REST encargado de exponer el endpoint de consulta de precios.
 * <p>
 * Implementa la interfaz generada por OpenAPI, delegando la lógica de negocio
 * al caso de uso {@link GetApplicablePriceUseCase}. Valida los parámetros de entrada
 * y transforma el modelo de dominio en un DTO de salida.
 */
@RestController
public class PriceController implements PricesApi {

    private final GetApplicablePriceUseCase getApplicablePriceUseCase;

    public PriceController(GetApplicablePriceUseCase getApplicablePriceUseCase) {
        this.getApplicablePriceUseCase = getApplicablePriceUseCase;
    }

    /**
     * Endpoint GET que retorna el precio aplicable para una combinación de fecha, producto y marca.
     *
     * @param consultationDate Fecha/hora de aplicación
     * @param productId        ID del producto
     * @param brandId          ID de la marca
     * @return DTO con los datos del precio aplicable
     */
    @Override
    public ResponseEntity<PriceResponseDto> getApplicablePrice(
        LocalDateTime consultationDate,
        Integer productId,
        Integer brandId
    ) {
        validateRequestParams(consultationDate, productId, brandId);

        // Si tu UseCase espera LocalDateTime, se convierte aquí
        Price price = getApplicablePriceUseCase.getApplicablePrice(
            consultationDate,
            productId,
            brandId
        );

        return ResponseEntity.ok(new PriceResponseDto(price));
    }

    private void validateRequestParams(LocalDateTime consultationDate, Integer productId, Integer brandId) {
        if (consultationDate == null) {
            throw new InvalidInputException("consultationDate is required");
        } else if (productId == null || productId <= 0) {
            throw new InvalidInputException("productId must be greater than 0");
        } else if (brandId == null || brandId <= 0) {
            throw new InvalidInputException("brandId must be greater than 0");
        }
    }
}
