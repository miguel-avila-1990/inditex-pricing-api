package com.inditex.pricing.infrastructure.out.persistence.adapter;

import com.inditex.pricing.domain.ports.out.ProductRepositoryPort;
import com.inditex.pricing.infrastructure.out.persistence.entity.ProductEntity;
import com.inditex.pricing.infrastructure.out.persistence.repository.JpaProductRepository;
import org.springframework.stereotype.Component;

/**
 * Adaptador que implementa el puerto ProductRepository
 * delegando en JpaProductRepository.
 */
@Component
public class ProductRepositoryAdapter implements ProductRepositoryPort {

    private final JpaProductRepository jpaProductRepository;

    public ProductRepositoryAdapter(JpaProductRepository jpaProductRepository) {
        this.jpaProductRepository = jpaProductRepository;
    }

    @Override
    public ProductEntity save(ProductEntity productEntity) {
        return jpaProductRepository.save(productEntity);
    }
}
