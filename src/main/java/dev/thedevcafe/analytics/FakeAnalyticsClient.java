package dev.thedevcafe.analytics;

import dev.thedevcafe.sale.Sale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Stand-in for the real HTTP client of the analytics service.
 * Set {@code devcafe.analytics.outage=true} to simulate the service being down.
 */
@Component
public class FakeAnalyticsClient implements AnalyticsClient {

    private static final Logger log = LoggerFactory.getLogger(FakeAnalyticsClient.class);

    private final boolean outage;

    public FakeAnalyticsClient(@Value("${devcafe.analytics.outage:false}") boolean outage) {
        this.outage = outage;
    }

    @Override
    public void send(Sale sale) {
        if (outage) {
            throw new AnalyticsUnavailableException("analytics.devcafe.internal: 503 Service Unavailable");
        }
        log.info("Analytics: sale {} of product {} ({} x {})",
                sale.getId(), sale.getProductId(), sale.getQuantity(), sale.getUnitPrice());
    }
}
