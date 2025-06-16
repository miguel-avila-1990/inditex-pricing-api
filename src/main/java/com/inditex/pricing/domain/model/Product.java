package com.inditex.pricing.domain.model;

/**
 * Representa un producto comercializado.
 *
 * @param id         ID único del producto
 * @param name       Nombre comercial
 * @param category   Tipo o categoría del producto
 * @param size       Talla del producto
 * @param gender     Género objetivo
 * @param color      Color principal
 * @param collection Colección a la que pertenece
 */
public record Product(
    Integer id,
    String name,
    String category,
    String size,
    String gender,
    String color,
    String collection
) {}
