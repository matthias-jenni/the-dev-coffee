-- ~40k online-shop sales over the last year, spread across the whole day (the shop is open 24/7).
INSERT INTO sale (product_id, quantity, unit_price, total, sold_at)
SELECT p.id,
       q.quantity,
       p.price,
       p.price * q.quantity,
       TIMESTAMPTZ '2025-09-01 00:00:00+00'
           + (g / 110) * INTERVAL '1 day'
           + ((g * 7877) % 86400) * INTERVAL '1 second'
FROM generate_series(1, 40000) AS g
         CROSS JOIN LATERAL (SELECT 1 + (g * 17) % 10 AS pid, CASE WHEN g % 9 = 0 THEN 3 WHEN g % 4 = 0 THEN 2 ELSE 1 END AS quantity) q
         JOIN product p ON p.id = 16 + q.pid;
