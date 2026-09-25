package dev.thedevcafe.sale;

import dev.thedevcafe.analytics.AnalyticsClient;
import dev.thedevcafe.common.NotFoundException;
import dev.thedevcafe.product.Product;
import dev.thedevcafe.product.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.Instant;

@Service
public class SaleService {

    private final SaleRepository saleRepository;
    private final ProductRepository productRepository;
    private final AnalyticsClient analyticsClient;
    private final Clock clock;

    public SaleService(SaleRepository saleRepository, ProductRepository productRepository,
                       AnalyticsClient analyticsClient, Clock clock) {
        this.saleRepository = saleRepository;
        this.productRepository = productRepository;
        this.analyticsClient = analyticsClient;
        this.clock = clock;
    }

    public Sale recordSale(Long productId, int quantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product " + productId + " not found"));

        Sale sale = new Sale(product.getId(), quantity, product.getPrice(), Instant.now(clock));
        Sale saved = saleRepository.save(sale);
        analyticsClient.send(saved);
        return saved;
    }
}
