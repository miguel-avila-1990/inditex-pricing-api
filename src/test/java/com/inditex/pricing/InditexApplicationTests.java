package com.inditex.pricing;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * Test básico de carga de contexto de Spring Boot.
 * <p>
 * Este test verifica que el contexto de la aplicación se puede cargar correctamente,
 * que todos los beans están bien configurados y que no existen problemas en la inicialización.
 * <p>
 * Es una red de seguridad para garantizar que la aplicación puede arrancar.
 * <p>
 * <strong>Importante:</strong> Se utiliza el perfil {@code test} para evitar la ejecución
 * de scripts SQL durante este test, ya que su objetivo no es probar la base de datos
 * ni los endpoints REST, sino únicamente validar la configuración global de Spring Boot.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ActiveProfiles("test")
class InditexApplicationTests {
    @Test
    void contextLoads() {
    }
}
