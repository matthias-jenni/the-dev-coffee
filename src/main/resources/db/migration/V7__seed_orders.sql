-- ~200k café orders over two years, 1-4 items each. Deterministic (no random()).
INSERT INTO cafe_order (id, customer_id, status, placed_at)
SELECT g,
       1 + (g * 7919) % 400,
       CASE WHEN g > 199950 THEN 'OPEN' WHEN g % 97 = 0 THEN 'CANCELLED' ELSE 'COMPLETED' END,
       TIMESTAMPTZ '2024-09-01 06:00:00+00'
           + (g / 274) * INTERVAL '1 day'
           + ((g * 37) % 780) * INTERVAL '1 minute'
FROM generate_series(1, 200000) AS g;

SELECT setval('cafe_order_id_seq', (SELECT max(id) FROM cafe_order));

INSERT INTO order_item (order_id, product_id, quantity, unit_price)
SELECT o.id,
       p.id,
       1 + (o.id + n) % 2,
       p.price
FROM cafe_order o
         CROSS JOIN generate_series(1, 4) AS n
         JOIN product p ON p.id = 1 + (o.id * 13 + n * 5) % 16
WHERE n <= 1 + (o.id * 31) % 4;

UPDATE cafe_order o
SET total = t.total
FROM (SELECT order_id, sum(quantity * unit_price) AS total FROM order_item GROUP BY order_id) t
WHERE t.order_id = o.id;
