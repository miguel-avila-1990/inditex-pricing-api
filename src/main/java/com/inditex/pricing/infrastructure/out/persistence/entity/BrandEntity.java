package com.inditex.pricing.infrastructure.out.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidad JPA que representa una marca del grupo.
 */
@Entity
@Table(name = "brands")
@Getter
@Setter
@NoArgsConstructor
public class BrandEntity {

    @Id
    private Integer id;

    private String name;

    private String country;

    private String description;

    private boolean active;
}
