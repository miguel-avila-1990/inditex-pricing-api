package com.inditex.pricing.infrastructure.out.persistence.repository;

import com.inditex.pricing.infrastructure.out.persistence.entity.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio JPA para la entidad Brand.
 */
public interface JpaBrandRepository extends JpaRepository<BrandEntity, Integer> {
}
