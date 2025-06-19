package com.inditex.pricing.infrastructure.config;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.inditex.pricing.domain.ports.out.BrandRepositoryPort;
import com.inditex.pricing.domain.ports.out.ProductRepositoryPort;
import com.inditex.pricing.infrastructure.out.persistence.entity.BrandEntity;
import com.inditex.pricing.infrastructure.out.persistence.entity.PriceEntity;
import com.inditex.pricing.infrastructure.out.persistence.entity.ProductEntity;
import com.inditex.pricing.infrastructure.out.persistence.repository.JpaPriceRepository;

/**
 * Carga de datos iniciales en la base de datos H2 en modo no-test.
 * Se ejecuta al arrancar la aplicación mediante ApplicationRunner.
 */
@Component
public class DataLoader implements ApplicationRunner {

    private final BrandRepositoryPort brandRepository;
    private final ProductRepositoryPort productRepository;
    private final JpaPriceRepository priceRepository;

    public DataLoader(BrandRepositoryPort brandRepository,
                      ProductRepositoryPort productRepository,
                      JpaPriceRepository priceRepository) {
        this.brandRepository = brandRepository;
        this.productRepository = productRepository;
        this.priceRepository = priceRepository;
    }

    @Override
    public void run(ApplicationArguments args) {

        // Crear Brand
        BrandEntity brand = new BrandEntity();
        brand.setId(1);
        brand.setName("ZARA");
        brand.setCountry("España");
        brand.setDescription("Marca líder en moda rápida del grupo Inditex.");
        brand.setActive(true);
        brandRepository.save(brand);

        // Crear Product
        ProductEntity product = new ProductEntity();
        product.setId(35455);
        product.setName("Camisa Slim Fit Hombre");
        product.setCategory("Camisas");
        product.setSize("L");
        product.setGender("Hombre");
        product.setColor("Azul");
        product.setCollection("Primavera/Verano");
        productRepository.save(product);

        // Crear Prices
        PriceEntity price1 = new PriceEntity();
        price1.setBrand(brand);
        price1.setProduct(product);
        price1.setStartDate(LocalDateTime.parse("2020-06-14T00:00:00"));
        price1.setEndDate(LocalDateTime.parse("2020-12-31T23:59:59"));
        price1.setPriceList(1);
        price1.setPriority(0);
        price1.setPrice(new BigDecimal("35.50"));
        price1.setCurrency("EUR");
        priceRepository.save(price1);

        PriceEntity price2 = new PriceEntity();
        price2.setBrand(brand);
        price2.setProduct(product);
        price2.setStartDate(LocalDateTime.parse("2020-06-14T15:00:00"));
        price2.setEndDate(LocalDateTime.parse("2020-06-14T18:30:00"));
        price2.setPriceList(2);
        price2.setPriority(1);
        price2.setPrice(new BigDecimal("25.45"));
        price2.setCurrency("EUR");
        priceRepository.save(price2);

        PriceEntity price3 = new PriceEntity();
        price3.setBrand(brand);
        price3.setProduct(product);
        price3.setStartDate(LocalDateTime.parse("2020-06-15T00:00:00"));
        price3.setEndDate(LocalDateTime.parse("2020-06-15T11:00:00"));
        price3.setPriceList(3);
        price3.setPriority(1);
        price3.setPrice(new BigDecimal("30.50"));
        price3.setCurrency("EUR");
        priceRepository.save(price3);

        PriceEntity price4 = new PriceEntity();
        price4.setBrand(brand);
        price4.setProduct(product);
        price4.setStartDate(LocalDateTime.parse("2020-06-15T16:00:00"));
        price4.setEndDate(LocalDateTime.parse("2020-12-31T23:59:59"));
        price4.setPriceList(4);
        price4.setPriority(1);
        price4.setPrice(new BigDecimal("38.95"));
        price4.setCurrency("EUR");
        priceRepository.save(price4);
    }
}
