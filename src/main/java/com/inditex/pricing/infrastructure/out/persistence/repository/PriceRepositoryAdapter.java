package com.inditex.pricing.infrastructure.out.persistence.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.inditex.pricing.domain.model.Price;
import com.inditex.pricing.domain.ports.out.PriceRepositoryPort;
import com.inditex.pricing.infrastructure.out.persistence.mapper.PriceMapper;

/**
 * Adaptador de salida que implementa el puerto {@link PriceRepositoryPort}.
 * <p>
 * Se encarga de acceder al repositorio JPA y adaptar los datos de persistencia
 * al modelo de dominio mediante el {@link PriceMapper}.
 */
@Repository
public class PriceRepositoryAdapter implements PriceRepositoryPort {

    private final JpaPriceRepository jpaPriceRepository;

    public PriceRepositoryAdapter(JpaPriceRepository jpaPriceRepository) {
        this.jpaPriceRepository = jpaPriceRepository;
    }
    
    /**
     * Busca el precio aplicable para un producto, marca y fecha determinada.
     * <p>
     * Se selecciona el primer precio encontrado ordenado por prioridad descendente,
     * utilizando una paginación de un solo resultado.
     *
     * @param applicationDate fecha/hora para la cual se desea obtener el precio
     * @param productId       identificador del producto
     * @param brandId         identificador de la cadena/marca
     * @return un {@link Price} si existe alguno aplicable; {@link Optional#empty()} si no
     */
    @Override
    public Optional<Price> findPrice(LocalDateTime applicationDate, Integer productId, Integer brandId) {
        return jpaPriceRepository
        		.findPriceByDateProductAndBrand(productId, brandId, applicationDate, PageRequest.of(0, 1))                
                .stream()
                .findFirst()
                .map(PriceMapper::toDomain);
    }
}
