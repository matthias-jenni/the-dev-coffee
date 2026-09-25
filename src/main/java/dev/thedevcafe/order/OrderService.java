package dev.thedevcafe.order;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final CafeOrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    public OrderService(CafeOrderRepository orderRepository, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    @Transactional(readOnly = true)
    public OrderHistoryResponse getOrderHistory(Long customerId) {
        List<CafeOrder> orders = orderRepository.findByCustomerIdOrderByPlacedAtDesc(customerId);

        List<OrderHistoryResponse.OrderSummary> summaries = new ArrayList<>();
        BigDecimal totalSpent = BigDecimal.ZERO;

        for (CafeOrder order : orders) {
            List<OrderItem> items = orderItemRepository.findByOrderId(order.getId());

            List<OrderHistoryResponse.Line> lines = items.stream()
                    .map(item -> new OrderHistoryResponse.Line(
                            item.getProduct().getName(), item.getQuantity(), item.getUnitPrice()))
                    .toList();

            summaries.add(new OrderHistoryResponse.OrderSummary(
                    order.getId(), order.getStatus(), order.getPlacedAt(), order.getTotal(), lines));

            if (order.getStatus() != OrderStatus.CANCELLED) {
                totalSpent = totalSpent.add(order.getTotal());
            }
        }

        return new OrderHistoryResponse(customerId, summaries.size(), totalSpent, summaries);
    }
}
