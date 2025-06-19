package com.inditex.pricing.infrastructure.out.persistence.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inditex.pricing.infrastructure.out.persistence.entity.PriceEntity;

@Repository
public interface JpaPriceRepository extends JpaRepository<PriceEntity, Long> {

    Optional<PriceEntity> findFirstByProduct_IdAndBrand_IdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
    	    Integer productId,
    	    Integer brandId,
    	    LocalDateTime applicationDate1,
    	    LocalDateTime applicationDate2
    	);
    
 
}
