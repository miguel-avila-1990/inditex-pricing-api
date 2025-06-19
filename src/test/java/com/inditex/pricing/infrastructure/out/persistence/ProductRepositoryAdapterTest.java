package com.inditex.pricing.infrastructure.out.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.inditex.pricing.infrastructure.out.persistence.adapter.ProductRepositoryAdapter;
import com.inditex.pricing.infrastructure.out.persistence.entity.ProductEntity;
import com.inditex.pricing.infrastructure.out.persistence.repository.JpaProductRepository;

class ProductRepositoryAdapterTest {

    @Mock
    private JpaProductRepository jpaProductRepository;

    @InjectMocks
    private ProductRepositoryAdapter productRepositoryAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should save product entity and return result")
    void shouldSaveProductEntity() {
        // Arrange
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(4);
        productEntity.setName("Camisa");

        when(jpaProductRepository.save(productEntity)).thenReturn(productEntity);

        // Act
        ProductEntity result = productRepositoryAdapter.save(productEntity);

        // Assert
        verify(jpaProductRepository, times(1)).save(productEntity);
        assertEquals(productEntity, result);
    }
}
