package com.lb.ecommerce.repository;

import com.lb.ecommerce.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
}
