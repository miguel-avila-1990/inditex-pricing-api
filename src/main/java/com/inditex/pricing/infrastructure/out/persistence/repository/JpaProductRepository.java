package com.inditex.pricing.infrastructure.out.persistence.repository;

import com.inditex.pricing.infrastructure.out.persistence.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio JPA para la entidad Product.
 */
public interface JpaProductRepository extends JpaRepository<ProductEntity, Integer> {
}
