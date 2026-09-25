package dev.thedevcafe.order;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CafeOrderRepository extends JpaRepository<CafeOrder, Long> {

    List<CafeOrder> findByCustomerIdOrderByPlacedAtDesc(Long customerId);
}
