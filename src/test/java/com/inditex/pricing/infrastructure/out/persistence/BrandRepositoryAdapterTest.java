package com.inditex.pricing.infrastructure.out.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.inditex.pricing.infrastructure.out.persistence.adapter.BrandRepositoryAdapter;
import com.inditex.pricing.infrastructure.out.persistence.entity.BrandEntity;
import com.inditex.pricing.infrastructure.out.persistence.repository.JpaBrandRepository;

class BrandRepositoryAdapterTest {

    @Mock
    private JpaBrandRepository jpaBrandRepository;

    @InjectMocks
    private BrandRepositoryAdapter brandRepositoryAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should save brand entity and return result")
    void shouldSaveBrandEntity() {
        // Arrange
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setId(1);
        brandEntity.setName("ZARA");

        when(jpaBrandRepository.save(brandEntity)).thenReturn(brandEntity);

        // Act
        BrandEntity result = brandRepositoryAdapter.save(brandEntity);

        // Assert
        verify(jpaBrandRepository, times(1)).save(brandEntity);
        assertEquals(brandEntity, result);
    }
}
