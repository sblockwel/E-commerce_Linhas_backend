package com.lb.ecommerce.repository;

import com.lb.ecommerce.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.math.BigDecimal;

@EnableJpaRepositories
public interface OrdersRepository extends JpaRepository<Orders, Long> {
    @Query(value = "select max(o.orderNumber+1)  from Orders o")
    public Long max();
}
