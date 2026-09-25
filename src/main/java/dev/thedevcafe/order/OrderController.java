package dev.thedevcafe.order;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * Order history shown on the customer's "My orders" page.
     */
    @GetMapping("/api/customers/{customerId}/orders")
    public OrderHistoryResponse orderHistory(@PathVariable Long customerId) {
        return orderService.getOrderHistory(customerId);
    }
}
