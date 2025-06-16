# INDITEX Test API

Este proyecto es una API REST desarrollada en Java 21 y Spring Boot como parte de una prueba técnica. Permite consultar el precio aplicable de un producto de una marca concreta para una fecha determinada, aplicando correctamente la prioridad en caso de solapamiento de tarifas.

## Requisitos

- Java 21
- Maven 3.6 o superior
- Docker (opcional)

---

## Compilación y ejecución

### Compilar el proyecto

```bash
mvn clean package
```
### Ejecutar la aplicación

```bash
java -jar target/inditex-test*.jar
```

## Uso con Docker

### Construir la imagen
```bash
docker build . -t inditex-test:1.0.0
```

### Ejecutar el contenedor
```bash
docker run -p 8080:8080 inditex-test:1.0.0
```

## Documentación del API

Una vez la aplicación esté en ejecución, puedes acceder a la documentación interactiva generada con Swagger en:

[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)


---

## Endpoint principal

GET `/api/prices`

Parámetros:

- `date` (formato ISO 8601): Fecha y hora de aplicación (por ejemplo: `2020-06-14T16:00:00`)
- `productId` (Long): ID del producto (por ejemplo: `35455`)
- `brandId` (Long): ID de la marca (por ejemplo: `1` para ZARA)

Ejemplo de solicitud:

```http
GET http://localhost:8080/api/prices?date=2020-06-14T16:00:00&productId=35455&brandId=1
```

Respuesta esperada:

```json
{
  "productId": 35455,
  "brandId": 1,
  "priceList": 2,
  "startDate": "2020-06-14T15:00:00",
  "endDate": "2020-06-14T18:30:00",
  "price": 25.45,
  "currency": "EUR"
}
```


## Testing

Se han implementado pruebas unitarias y de aceptación para garantizar el correcto funcionamiento de la lógica de negocio y del endpoint principal.

### Pruebas unitarias

Se cubren:

- `GetApplicablePriceUseCaseImplTest`: valida el comportamiento del caso de uso principal.
- `PriceRepositoryAdapterTest`: comprueba el mapeo entre entidad JPA y modelo de dominio.
- `PriceControllerTest`: cubre el controlador REST usando `@WebMvcTest` y MockMvc.
- Mapeadores: se validan conversiones entre entidades y objetos del dominio.

### Pruebas de aceptación (Cucumber)

Se validan los 5 escenarios definidos en el enunciado, que combinan distintas fechas, productos y marcas para asegurar que el precio devuelto es el correcto.  
Estas pruebas actúan como validación de extremo a extremo utilizando `TestRestTemplate` y JSONPath.

Para ejecutarlas:

```bash
mvn -Dtest=com.inditex.demo.cucumber.RunCucumberTest test
```

### Ejecutar tests unitarios
```bash
mvn test
```

---

## Estructura del proyecto

La aplicación sigue una arquitectura hexagonal basada en DDD, con una separación clara de responsabilidades por capas:

- **Dominio (`domain.model`)**: contiene las entidades principales (`Price`, `Product`, `Brand`) modeladas como `record` para asegurar inmutabilidad y simplicidad. `Price` referencia explícitamente a `Product` y `Brand`, evitando el uso de identificadores primitivos sueltos.

- **Casos de uso (`application.usecase`)**: encapsulan la lógica de aplicación. El caso principal implementado es `GetApplicablePriceUseCaseImpl`, que orquesta la obtención del precio correcto en base a los criterios indicados.

- **Puertos (`domain.ports`)**: definen las interfaces de entrada y salida que permiten aislar el dominio tanto de la exposición REST como de la persistencia.

- **Infraestructura (`infrastructure`)**:
  - `rest`: controlador, DTOs y manejo de excepciones.
  - `persistence`: entidades JPA, repositorio y adaptador al puerto de salida.
  - `mapper`: conversión entre entidades JPA y objetos del dominio.

---

## Modelado del dominio

`Price` está relacionado directamente con `Product` y `Brand` como objetos, no como IDs. Esto permite un modelo más expresivo y alineado con el negocio. Las entidades JPA están separadas del modelo del dominio y conectadas mediante mapeadores. Las relaciones en JPA están correctamente definidas como `@ManyToOne`, dado que un producto o marca pueden tener múltiples tarifas.

---

## Manejo de errores

Se han definido excepciones específicas como `PriceNotFoundException` e `InvalidInputException`, y se exponen mediante un manejador global (`GlobalExceptionHandler`). Los códigos de error se agrupan en una clase separada (`ErrorCodes`), evitando declarar constantes en el manejador y facilitando su reutilización.

---

## Contrato OpenAPI

El proyecto utiliza OpenAPI Generator para generar la interfaz del controlador (`PricesApi`) automáticamente desde `openapi.yaml`.

- Archivo fuente: `src/main/resources/openapi.yaml`
- Código generado en: `target/generated-sources/openapi`

---

## Base de datos

Se utiliza H2 en memoria. Los datos se inicializan automáticamente al arrancar la aplicación.

Consola H2:  
http://localhost:8080/h2-console

- Driver: org.h2.Driver  
- URL: jdbc:h2:mem:testdb  
- Usuario: sa  
- Contraseña: *(vacía)*

---

## Autor

Miguel Ángel Ávila

```