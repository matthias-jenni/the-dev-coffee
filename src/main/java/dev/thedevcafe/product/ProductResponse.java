package dev.thedevcafe.product;

import java.math.BigDecimal;

public record ProductResponse(Long id, String name, Category category, BigDecimal price, int stock) {

    static ProductResponse from(Product product) {
        return new ProductResponse(product.getId(), product.getName(), product.getCategory(),
                product.getPrice(), product.getStock());
    }
}
