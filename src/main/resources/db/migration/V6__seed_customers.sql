INSERT INTO customer (id, name, email, created_at)
SELECT g,
       (ARRAY ['Ada', 'Grace', 'Linus', 'Margaret', 'Dennis', 'Barbara', 'Ken', 'Frances', 'Alan', 'Radia'])[1 + g % 10]
           || ' ' ||
       (ARRAY ['Lovelace', 'Hopper', 'Torvalds', 'Hamilton', 'Ritchie', 'Liskov', 'Thompson', 'Allen', 'Turing', 'Perlman'])[1 + (g / 10) % 10],
       'customer' || g || '@example.com',
       TIMESTAMPTZ '2024-06-01 00:00:00+00' + (g % 400) * INTERVAL '1 day'
FROM generate_series(1, 400) AS g;

SELECT setval('customer_id_seq', (SELECT max(id) FROM customer));
