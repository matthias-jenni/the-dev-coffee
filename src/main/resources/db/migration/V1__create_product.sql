CREATE TABLE product
(
    id       BIGSERIAL PRIMARY KEY,
    name     VARCHAR(100)   NOT NULL,
    category VARCHAR(20)    NOT NULL,
    price    NUMERIC(10, 2) NOT NULL CHECK (price >= 0),
    stock    INTEGER        NOT NULL DEFAULT 0
);
