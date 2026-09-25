# Module 3: Review This PR

> A teammate, possibly with a lot of AI help, opened this PR. Review it.
> Would you approve it, ask for changes, or reject it?

## The PR

- **Ticket:** DEVCAFE-142, *Daily revenue per product on the dashboard*.
  See [`src/main/java/dev/thedevcafe/reporting/README.md`](../../src/main/java/dev/thedevcafe/reporting/README.md).
- **Branch:** `pr/daily-revenue-report` (compare against `main`)

```sh
git fetch origin
git diff main...origin/pr/daily-revenue-report
```

You may check out the branch, run it and use AI, the same as you would in a real review.

## Context on `main`

| What | File |
|---|---|
| Sale entity | `src/main/java/dev/thedevcafe/sale/Sale.java` |
| Sale repository | `src/main/java/dev/thedevcafe/sale/SaleRepository.java` |
| Table + test data (~40k sales) | `src/main/resources/db/migration/V4__create_sale.sql`, `V8__seed_sales.sql` |
| Config | `src/main/resources/application.yml` |
