Feature: Testing API endpoint /prices

  Scenario: Realizar una petición a las 10:00 del día 14 para el producto 35455 y la marca 1 (ZARA).
    Given I send a GET request to "/prices?consultationDate=2020-06-14T10:00:00&productId=35455&brandId=1"
    Then the response status should be 200
    And the response body path "$.price" should be 35.50
    And the response body path "$.currency" should be "EUR"

  Scenario: Realizar una petición a las 16:00 del día 14 para el producto 35455 y la marca 1 (ZARA).
    Given I send a GET request to "/prices?consultationDate=2020-06-14T16:00:00&productId=35455&brandId=1"
    Then the response status should be 200
    And the response body path "$.price" should be 25.45
    And the response body path "$.currency" should be "EUR"

  Scenario: Realizar una petición a las 21:00 del día 14 para el producto 35455 y la marca 1 (ZARA).
    Given I send a GET request to "/prices?consultationDate=2020-06-14T21:00:00&productId=35455&brandId=1"
    Then the response status should be 200
    And the response body path "$.price" should be 35.50
    And the response body path "$.currency" should be "EUR"

  Scenario: Realizar una petición a las 10:00 del día 15 para el producto 35455 y la marca 1 (ZARA).
    Given I send a GET request to "/prices?consultationDate=2020-06-15T10:00:00&productId=35455&brandId=1"
    Then the response status should be 200
    And the response body path "$.price" should be 30.50
    And the response body path "$.currency" should be "EUR"

  Scenario: Realizar una petición a las 21:00 del día 16 para el producto 35455 y la marca 1 (ZARA).
    Given I send a GET request to "/prices?consultationDate=2020-06-16T21:00:00&productId=35455&brandId=1"
    Then the response status should be 200
    And the response body path "$.price" should be 38.95
    And the response body path "$.currency" should be "EUR"

  Scenario: Realizar una petición sin resultado.
    Given I send a GET request to "/prices?consultationDate=2025-06-16T21:00:00&productId=35455&brandId=1"
    Then the response status should be 404
    And the response body should contain "PRICE_NOT_FOUND"

  Scenario: Realizar una petición sin algún parámetro.
    Given I send a GET request to "/prices?productId=35455&brandId=1"
    Then the response status should be 400
    And the response body should contain "MISSING_PARAMETER"

  Scenario: Realizar una petición con algún parámetro inválido.
    Given I send a GET request to "/prices?consultationDate=2025-06-16T21:00:00&productId=35455&brandId=-1"
    Then the response status should be 400
    And the response body should contain "INVALID_INPUT"
