CREATE TABLE cafe_order
(
    id          BIGSERIAL PRIMARY KEY,
    customer_id BIGINT         NOT NULL REFERENCES customer (id),
    status      VARCHAR(20)    NOT NULL,
    placed_at   TIMESTAMPTZ    NOT NULL,
    total       NUMERIC(10, 2) NOT NULL DEFAULT 0
);

CREATE TABLE order_item
(
    id         BIGSERIAL PRIMARY KEY,
    order_id   BIGINT         NOT NULL REFERENCES cafe_order (id),
    product_id BIGINT         NOT NULL REFERENCES product (id),
    quantity   INTEGER        NOT NULL CHECK (quantity > 0),
    unit_price NUMERIC(10, 2) NOT NULL
);
