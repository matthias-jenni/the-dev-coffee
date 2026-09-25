package dev.thedevcafe.reporting;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DailyRevenue(LocalDate date, Long productId, String productName, long unitsSold, BigDecimal revenue) {
}
