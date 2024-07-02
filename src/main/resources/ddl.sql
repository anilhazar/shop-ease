CREATE DATABASE IF NOT EXISTS shop_ease;

USE shop_ease;

CREATE TABLE product
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

CREATE TABLE customer
(
    id         VARCHAR(36) PRIMARY KEY,
    name       VARCHAR(255) NOT NULL,
    email      VARCHAR(255) NOT NULL UNIQUE,
    password   VARCHAR(255) NOT NULL,
    is_deleted boolean      NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE cart
(
    id          VARCHAR(36) PRIMARY KEY,
    customer_id CHAR(36) NOT NULL,
    total_price DECIMAL(10, 2) DEFAULT 0.0,
    is_deleted  boolean  NOT NULL,
    created_at  TIMESTAMP      DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP      DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk__customer_id_cart FOREIGN KEY (customer_id) REFERENCES Customer (id)
);

CREATE TABLE cart_item
(
    id         VARCHAR(36) PRIMARY KEY,
    cart_id    CHAR(36)       NOT NULL,
    product_id CHAR(36)       NOT NULL,
    quantity   BIGINT         NOT NULL,
    price      DECIMAL(10, 2) NOT NULL,
    is_deleted boolean        NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk__cart_id_cart_item FOREIGN KEY (cart_id) REFERENCES Cart (id),
    CONSTRAINT fk__product_id_cart_item FOREIGN KEY (product_id) REFERENCES Product (id)
);

CREATE TABLE `order`
(
    id          VARCHAR(36) PRIMARY KEY,
    customer_id CHAR(36)       NOT NULL,
    total_price DECIMAL(10, 2) NOT NULL,
    is_deleted  boolean        NOT NULL,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk__customer_id_order FOREIGN KEY (customer_id) REFERENCES Customer (id)
);

CREATE TABLE order_item
(
    id         VARCHAR(36) PRIMARY KEY,
    order_id   CHAR(36)       NOT NULL,
    product_id CHAR(36)       NOT NULL,
    price      DECIMAL(10, 2) NOT NULL,
    quantity   BIGINT         NOT NULL,
    is_deleted boolean        NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk__order_id_order_item FOREIGN KEY (order_id) REFERENCES `order` (id),
    CONSTRAINT fk__product_id_order_item FOREIGN KEY (product_id) REFERENCES Product (id)
);

-- Dummy data for test

