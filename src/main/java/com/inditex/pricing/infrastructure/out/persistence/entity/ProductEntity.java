package com.inditex.pricing.infrastructure.out.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidad JPA que representa un producto comercial.
 */
@Entity
@Table(
	    name = "products",
	    indexes = {
	        @Index(name = "idx_products_name", columnList = "name"),
	        @Index(name = "idx_products_category", columnList = "category")
	    }
	)
@Getter
@Setter
@NoArgsConstructor
public class ProductEntity {

    @Id
    private Integer id;

    private String name;

    private String category;

    private String size;

    private String gender;

    private String color;

    private String collection;
}
