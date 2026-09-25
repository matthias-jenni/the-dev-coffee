-- Retail sales from the online shop (beans, merch). Café counter orders live in cafe_order.
CREATE TABLE sale
(
    id         BIGSERIAL PRIMARY KEY,
    product_id BIGINT         NOT NULL REFERENCES product (id),
    quantity   INTEGER        NOT NULL CHECK (quantity > 0),
    unit_price NUMERIC(10, 2) NOT NULL,
    total      NUMERIC(12, 2) NOT NULL,
    sold_at    TIMESTAMPTZ    NOT NULL DEFAULT now()
);

CREATE INDEX idx_sale_sold_at ON sale (sold_at);
