package com.inditex.pricing.infrastructure.out.persistence.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.inditex.pricing.infrastructure.out.persistence.entity.PriceEntity;

@Repository
public interface JpaPriceRepository extends JpaRepository<PriceEntity, Long> {

    @Query("""
        SELECT p FROM PriceEntity p
    	WHERE p.product.id = :productId
    			AND p.brand.id = :brandId
    			AND :applicationDate BETWEEN p.startDate AND p.endDate
        ORDER BY p.priority DESC
    """)
    List<PriceEntity> findPriceByDateProductAndBrand(
    	    @Param("productId") Integer productId,
    	    @Param("brandId") Integer brandId,
    	    @Param("applicationDate") LocalDateTime applicationDate,
    	    Pageable pageable
    	);
}
