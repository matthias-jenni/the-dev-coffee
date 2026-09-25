package dev.thedevcafe.order;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public record OrderHistoryResponse(Long customerId, int orderCount, BigDecimal totalSpent, List<OrderSummary> orders) {

    public record OrderSummary(Long id, OrderStatus status, Instant placedAt, BigDecimal total, List<Line> items) {
    }

    public record Line(String product, int quantity, BigDecimal unitPrice) {
    }
}
