package dev.thedevcafe.reporting;

import dev.thedevcafe.product.Product;
import dev.thedevcafe.product.ProductRepository;
import dev.thedevcafe.sale.Sale;
import dev.thedevcafe.sale.SaleRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Service
public class DailyRevenueReportService {

    private final SaleRepository saleRepository;
    private final ProductRepository productRepository;

    public DailyRevenueReportService(SaleRepository saleRepository, ProductRepository productRepository) {
        this.saleRepository = saleRepository;
        this.productRepository = productRepository;
    }

    public List<DailyRevenue> dailyRevenue(LocalDate from, LocalDate to) {
        Map<Long, String> productNames = productRepository.findAll().stream()
                .collect(Collectors.toMap(Product::getId, Product::getName));

        Map<LocalDate, Map<Long, List<Sale>>> salesByDayAndProduct = saleRepository.findAll().stream()
                .filter(sale -> isBetweenInclusive(toDate(sale.getSoldAt()), from, to))
                .collect(Collectors.groupingBy(sale -> toDate(sale.getSoldAt()), TreeMap::new,
                        Collectors.groupingBy(Sale::getProductId, TreeMap::new, Collectors.toList())));

        List<DailyRevenue> report = new ArrayList<>();
        salesByDayAndProduct.forEach((date, salesByProduct) -> salesByProduct.forEach((productId, sales) -> {
            long unitsSold = sales.stream().mapToLong(Sale::getQuantity).sum();
            BigDecimal revenue = sales.stream().map(Sale::getTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
            report.add(new DailyRevenue(date, productId, productNames.get(productId), unitsSold, revenue));
        }));
        return report;
    }

    // sold_at is stored in UTC
    private static LocalDate toDate(Instant soldAt) {
        return soldAt.atZone(ZoneOffset.UTC).toLocalDate();
    }

    private static boolean isBetweenInclusive(LocalDate date, LocalDate from, LocalDate to) {
        return date.isAfter(from) && date.isBefore(to);
    }
}
