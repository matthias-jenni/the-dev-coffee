# Module 4: When Things Go Wrong

Two short snippets to read and discuss. Nothing to implement.

## Snippet A: recording a sale (Java)

`src/main/java/dev/thedevcafe/sale/SaleService.java`, method `recordSale`

It saves a sale, then tells the analytics service about it
(`src/main/java/dev/thedevcafe/analytics/AnalyticsClient.java`).

> The analytics service goes down for thirty seconds. Walk us through what happens.

To see it live, start the app with the analytics service "down":

```sh
./mvnw spring-boot:run -Dspring-boot.run.arguments=--devcafe.analytics.outage=true
curl -i -X POST http://localhost:8080/api/sales -H 'Content-Type: application/json' -d '{"productId": 17, "quantity": 1}'
```

## Snippet B: the Buy button (React)

[`BuyButton.jsx`](BuyButton.jsx)

> This drops the number on screen right away, before the server confirms. Good idea or bad idea?
