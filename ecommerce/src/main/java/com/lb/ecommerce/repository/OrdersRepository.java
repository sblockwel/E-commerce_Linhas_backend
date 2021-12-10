package com.lb.ecommerce.repository;

import com.lb.ecommerce.entity.Order;
import com.lb.ecommerce.models.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Order, Long> {
    @Query("select o from Order o where status in (0, 1, 2)")
    List<Order> getPending();
}
