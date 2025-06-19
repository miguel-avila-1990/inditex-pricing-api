package com.inditex.pricing.infrastructure.out.persistence.adapter;

import com.inditex.pricing.domain.ports.out.BrandRepositoryPort;
import com.inditex.pricing.infrastructure.out.persistence.entity.BrandEntity;
import com.inditex.pricing.infrastructure.out.persistence.repository.JpaBrandRepository;
import org.springframework.stereotype.Component;

/**
 * Adaptador que implementa el puerto BrandRepository
 * delegando en JpaBrandRepository.
 */
@Component
public class BrandRepositoryAdapter implements BrandRepositoryPort {

    private final JpaBrandRepository jpaBrandRepository;

    public BrandRepositoryAdapter(JpaBrandRepository jpaBrandRepository) {
        this.jpaBrandRepository = jpaBrandRepository;
    }

    @Override
    public BrandEntity save(BrandEntity brandEntity) {
        return jpaBrandRepository.save(brandEntity);
    }
}
