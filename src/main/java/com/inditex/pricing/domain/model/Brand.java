package com.inditex.pricing.domain.model;

/**
 * Representa una marca del grupo.
 *
 * @param id          ID único de la marca
 * @param name        Nombre de la marca
 * @param country     País de origen
 * @param description Descripción comercial
 * @param active      Si está activa o no
 */
public record Brand(
    Integer id,
    String name,
    String country,
    String description,
    boolean active
) {}
