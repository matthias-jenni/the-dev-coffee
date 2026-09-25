package dev.thedevcafe.sale;

import java.math.BigDecimal;
import java.time.Instant;

public record SaleResponse(Long id, Long productId, int quantity, BigDecimal unitPrice, BigDecimal total, Instant soldAt) {

    static SaleResponse from(Sale sale) {
        return new SaleResponse(sale.getId(), sale.getProductId(), sale.getQuantity(),
                sale.getUnitPrice(), sale.getTotal(), sale.getSoldAt());
    }
}
