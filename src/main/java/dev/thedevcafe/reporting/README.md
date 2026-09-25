# Reporting

## Ticket DEVCAFE-142: Daily revenue per product on the dashboard

**Requested by:** Management / shop owner

> "On the dashboard I want to see, for a date range I pick, how much revenue each product made
> per day in the online shop. One row per day and product, with the number of units sold and
> the revenue. Days are shop days, so Swiss local time (`Europe/Zurich`), not UTC."

**Acceptance criteria**
- `GET /api/reports/daily-revenue?from=2026-03-01&to=2026-03-31`
- `from` and `to` are local dates (inclusive), interpreted in the shop time zone
  (`devcafe.shop-time-zone`).
- Response: a list of `{ date, productId, productName, unitsSold, revenue }`, sorted by date, then product.
- Revenue in CHF with 2 decimal places.

**Data:** the `sale` table (`dev.thedevcafe.sale.Sale`). `sold_at` is a `timestamptz` (stored in UTC)
and `total` is `NUMERIC(12,2)`.

**Status:** in review.
