# Module 2: Make It Fast

> This endpoint takes about five seconds. Support says it's getting slower as data grows.
> Find out why, and fix it.
>
> At the end: **show us a number.** How slow was it before, how fast is it now,
> and how do you know it was your change that did it?

## The endpoint

The customer's "My orders" page calls:

```
GET http://localhost:8080/api/customers/{customerId}/orders
```

Try it with a customer who has a long history, e.g. customer `42`:

```sh
curl -s -o /dev/null -w "%{time_total}s\n" http://localhost:8080/api/customers/42/orders
```

## Where to look

| What | File |
|---|---|
| Controller | `src/main/java/dev/thedevcafe/order/OrderController.java` |
| Service | `src/main/java/dev/thedevcafe/order/OrderService.java` |
| Repositories | `src/main/java/dev/thedevcafe/order/CafeOrderRepository.java`, `OrderItemRepository.java` |
| Entities | `src/main/java/dev/thedevcafe/order/CafeOrder.java`, `OrderItem.java` |
| Tables | `src/main/resources/db/migration/V3__create_cafe_order.sql` |
| Test data (~200k orders) | `src/main/resources/db/migration/V7__seed_orders.sql` |
| Config | `src/main/resources/application.yml` |

**SQL console:** http://localhost:5050 (pgAdmin, already connected to the `devcoffee` database).

Schema changes go in a new Flyway migration in `src/main/resources/db/migration` (e.g. `V9__...sql`);
it's applied automatically on the next app start.
