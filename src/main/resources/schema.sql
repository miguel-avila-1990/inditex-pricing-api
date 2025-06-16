CREATE TABLE brands (
    id INT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    country VARCHAR(50),
    description VARCHAR(255),
    active BOOLEAN DEFAULT TRUE
);

CREATE TABLE products (
    id INT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    category VARCHAR(50),
    size VARCHAR(10),
    gender VARCHAR(20),
    color VARCHAR(30),
    collection VARCHAR(100)
);

CREATE TABLE prices (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    brand_id INT NOT NULL,
    start_date TIMESTAMP NOT NULL,
    end_date TIMESTAMP NOT NULL,
    price_list INT NOT NULL,
    product_id INT NOT NULL,
    priority INT NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    curr VARCHAR(3) NOT NULL,
    CONSTRAINT fk_brand FOREIGN KEY (brand_id) REFERENCES brands(id),
    CONSTRAINT fk_product FOREIGN KEY (product_id) REFERENCES products(id)
);

-- Índice para optimizar la búsqueda del precio aplicable
CREATE INDEX idx_price_lookup ON prices (product_id, brand_id, start_date, end_date, priority);