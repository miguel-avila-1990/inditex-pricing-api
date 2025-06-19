package com.inditex.pricing.infrastructure.out.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Entidad JPA que representa una tarifa de precio para un producto en una marca.
 */
@Entity
@Table(
	    name = "prices",
	    indexes = {@Index(name = "idx_prices_lookup", columnList = "product_id, brand_id, start_date, end_date, priority")}
	  )
@Getter
@Setter
@NoArgsConstructor
public class PriceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    private BrandEntity brand;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;

    @Column(name = "price_list")
    private Integer priceList;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    private Integer priority;

    private BigDecimal price;

    @Column(name = "curr")
    private String currency;
}
