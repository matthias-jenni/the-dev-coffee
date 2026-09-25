package dev.thedevcafe.reporting;

import dev.thedevcafe.TestcontainersConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;

import static org.assertj.core.api.Assertions.assertThat;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class DailyRevenueReportServiceTest {

    @Autowired
    DailyRevenueReportService reportService;

    @Test
    void reportsRevenuePerDayAndProduct() {
        var report = reportService.dailyRevenue(LocalDate.of(2026, 3, 1), LocalDate.of(2026, 3, 31));

        assertThat(report).isNotEmpty();
        assertThat(report).isSortedAccordingTo(
                Comparator.comparing(DailyRevenue::date).thenComparing(DailyRevenue::productId));
        assertThat(report).allSatisfy(row -> {
            assertThat(row.productName()).isNotBlank();
            assertThat(row.unitsSold()).isPositive();
            assertThat(row.revenue()).isGreaterThan(BigDecimal.ZERO);
        });
    }
}
