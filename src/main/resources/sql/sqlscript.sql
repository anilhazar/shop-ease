CREATE DATABASE IF NOT EXISTS shop_ease;

USE shop_ease;

CREATE USER IF NOT EXISTS '${SHOP_EASE_DB_USERNAME}'@'%' IDENTIFIED BY '${SHOP_EASE_DB_PASSWORD}';
GRANT ALL PRIVILEGES ON shop_ease.* TO '${SHOP_EASE_DB_USERNAME}'@'%';
FLUSH PRIVILEGES;


CREATE TABLE IF NOT EXISTS product
(
    id          VARCHAR(36) PRIMARY KEY,
    name        VARCHAR(255)                                            NOT NULL,
    description TEXT,
    price       DECIMAL(10, 2)                                          NOT NULL,
    stock       BIGINT                                                  NOT NULL,
    category    ENUM ('ELECTRONIC', 'FASHION', 'HOME', 'FOOD', 'SPORT') NOT NULL,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS customer
(
    id         VARCHAR(36) PRIMARY KEY,
    name       VARCHAR(255) NOT NULL,
    email      VARCHAR(255) NOT NULL UNIQUE,
    password   VARCHAR(255) NOT NULL,
    is_deleted BOOLEAN      NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS cart
(
    id          VARCHAR(36) PRIMARY KEY,
    customer_id CHAR(36) NOT NULL,
    total_price DECIMAL(10, 2) DEFAULT 0.0,
    is_deleted  BOOLEAN  NOT NULL,
    created_at  TIMESTAMP      DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP      DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk__customer_id_cart FOREIGN KEY (customer_id) REFERENCES customer (id)
);

CREATE TABLE IF NOT EXISTS cart_item
(
    id         VARCHAR(36) PRIMARY KEY,
    cart_id    CHAR(36)       NOT NULL,
    product_id CHAR(36)       NOT NULL,
    quantity   BIGINT         NOT NULL,
    price      DECIMAL(10, 2) NOT NULL,
    is_deleted BOOLEAN        NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk__cart_id_cart_item FOREIGN KEY (cart_id) REFERENCES cart (id),
    CONSTRAINT fk__product_id_cart_item FOREIGN KEY (product_id) REFERENCES product (id)
);

CREATE TABLE IF NOT EXISTS `order`
(
    id          VARCHAR(36) PRIMARY KEY,
    customer_id CHAR(36)       NOT NULL,
    total_price DECIMAL(10, 2) NOT NULL,
    is_deleted  BOOLEAN        NOT NULL,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk__customer_id_order FOREIGN KEY (customer_id) REFERENCES customer (id)
);

CREATE TABLE IF NOT EXISTS order_item
(
    id         VARCHAR(36) PRIMARY KEY,
    order_id   CHAR(36)       NOT NULL,
    product_id CHAR(36)       NOT NULL,
    price      DECIMAL(10, 2) NOT NULL,
    quantity   BIGINT         NOT NULL,
    is_deleted BOOLEAN        NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk__order_id_order_item FOREIGN KEY (order_id) REFERENCES `order` (id),
    CONSTRAINT fk__product_id_order_item FOREIGN KEY (product_id) REFERENCES product (id)
);

-- DQL to Generate Dummy Data for Tests
INSERT INTO product (id, name, description, price, stock, category)
VALUES (UUID(), 'Laptop', 'High performance laptop', 1200.00, 10, 'ELECTRONIC'),
       (UUID(), 'T-Shirt', 'Comfortable cotton t-shirt', 20.00, 50, 'FASHION');

INSERT INTO customer (id, name, email, password, is_deleted)
VALUES (UUID(), 'Alice', 'alice@example.com', 'password123', FALSE),
       (UUID(), 'Bob', 'bob@example.com', 'password123', FALSE);
