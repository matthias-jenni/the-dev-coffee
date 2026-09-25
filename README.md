# ☕ The Dev Café

A small coffee shop API: café counter orders, an online shop for beans & merch, and (soon) a dashboard.
It also hosts our technical interview exercises. **Start here: [`interview/README.md`](interview/README.md).**

**Stack:** Java 25 · Spring Boot 4.1 · Maven · Spring Data JPA · Flyway · PostgreSQL 17 · pgAdmin

## Run it

**In the dev container / Codespace** (recommended): everything is set up. Then run:

```sh
./mvnw spring-boot:run
```

**Locally**: needs Java 25 and Docker.

```sh
docker compose up -d db pgadmin   # Postgres on localhost:5433, pgAdmin on localhost:5050
./mvnw spring-boot:run
```

On first start, Flyway creates the schema and seeds test data (~200k orders, ~40k sales). This takes a few seconds.

| Service | URL |
|---|---|
| API | http://localhost:8080 |
| pgAdmin (no login, pre-connected to `devcoffee`) | http://localhost:5050 |

## API

| Method | Path | Description |
|---|---|---|
| `GET` | `/api/products?category=BEANS` | Products (optionally filtered by `DRINK`, `FOOD`, `BEANS`, `MERCH`) |
| `GET` | `/api/products/{id}` | One product |
| `GET` | `/api/customers/{id}/orders` | A customer's café order history |
| `POST` | `/api/sales` | Record an online-shop sale: `{"productId": 17, "quantity": 1}` |
| `GET` | `/api/sales/{id}` | One sale |

## Project layout

```
src/main/java/dev/thedevcafe/
  product/     products & stock
  order/       café counter orders (cafe_order, order_item)
  sale/        online-shop sales
  analytics/   client for the marketing analytics service (faked; devcafe.analytics.outage=true to fail)
  reporting/   dashboard reports (see reporting/README.md)
src/main/resources/db/migration/   Flyway migrations (schema + seed data)
interview/                         interview exercises, one folder per module
```

## Tests

```sh
./mvnw verify   # uses Testcontainers, needs Docker
```

## Database

- Connection: `jdbc:postgresql://localhost:5433/devcoffee`, user `coffeeuser`, password `coffeepass`
  (inside the dev container, host `db` and port `5432`).
- Resetting the data: `docker compose down -v` removes the database and pgAdmin volumes.
