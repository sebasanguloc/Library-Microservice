DROP TABLE IF EXISTS order_items;
DROP TABLE IF EXISTS orders;

CREATE TABLE orders (
                        id BIGSERIAL PRIMARY KEY,
                        user_email VARCHAR(255) NOT NULL,
                        status VARCHAR(20) NOT NULL CHECK (status IN ('PENDING', 'PAID', 'CANCELLED', 'SHIPPED')),
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE order_items (
                             id BIGSERIAL PRIMARY KEY,
                             order_id BIGINT NOT NULL,
                             book_title VARCHAR(50) NOT NULL,
                             quantity INT NOT NULL CHECK (quantity > 0),
                             price NUMERIC(10,2) NOT NULL CHECK (price >= 0),
                             FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE
);