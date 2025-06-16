package com.inditex.pricing.application.usecase;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inditex.pricing.domain.exceptions.InvalidInputException;
import com.inditex.pricing.domain.exceptions.PriceNotFoundException;
import com.inditex.pricing.domain.model.Price;
import com.inditex.pricing.domain.ports.in.GetApplicablePriceUseCase;
import com.inditex.pricing.domain.ports.out.PriceRepositoryPort;

/**
 * Caso de uso que implementa la lógica de búsqueda del precio aplicable
 * para un producto en una marca concreta y fecha determinada.
 */
@Service
public class GetApplicablePriceUseCaseImpl implements GetApplicablePriceUseCase {
    private final PriceRepositoryPort priceRepository;
    private static final String ERROR_MESSAGE_NOT_APPLICABLE_PRICE_FOUND = "No applicable price found";


    public GetApplicablePriceUseCaseImpl(PriceRepositoryPort priceRepository) {
        this.priceRepository = priceRepository;
    }
    
    /**
     * Obtiene el precio aplicable para una combinación de producto, marca y fecha.
     *
     * @param applicationDate Fecha/hora de aplicación del precio
     * @param productId       ID del producto
     * @param brandId         ID de la marca
     * @return {@link Price} si existe alguno aplicable
     * @throws InvalidInputException     si alguno de los parámetros es null
     * @throws PriceNotFoundException    si no se encuentra precio válido
     */
    @Override
    @Transactional(readOnly = true)
    public Price getApplicablePrice(LocalDateTime applicationDate, Integer productId, Integer brandId) {
        validateInput(applicationDate, productId, brandId);

        return priceRepository.findPrice(applicationDate, productId, brandId)
                .orElseThrow(() -> new PriceNotFoundException(ERROR_MESSAGE_NOT_APPLICABLE_PRICE_FOUND));
    }

    private void validateInput(LocalDateTime applicationDate, Integer productId, Integer brandId) {
        requireNonNull(applicationDate, "applicationDate");
        requireNonNull(productId, "productId");
        requireNonNull(brandId, "brandId");
    }

    private void requireNonNull(Object value, String fieldName) {
        if (value == null) {
            throw new InvalidInputException(fieldName + " must not be null");
        }
    }



}
