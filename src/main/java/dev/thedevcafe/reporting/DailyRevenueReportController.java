package dev.thedevcafe.reporting;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class DailyRevenueReportController {

    private final DailyRevenueReportService reportService;

    public DailyRevenueReportController(DailyRevenueReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/api/reports/daily-revenue")
    public List<DailyRevenue> dailyRevenue(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
                                           @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
        return reportService.dailyRevenue(from, to);
    }
}
