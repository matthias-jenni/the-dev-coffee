package dev.thedevcafe.analytics;

import dev.thedevcafe.sale.Sale;

/**
 * Client for the marketing analytics service, which counts every sale.
 */
public interface AnalyticsClient {

    void send(Sale sale);
}
