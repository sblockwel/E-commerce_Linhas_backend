package com.lb.ecommerce.repository;

import com.lb.ecommerce.entity.Orders;
import com.lb.ecommerce.models.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.math.BigDecimal;
import java.util.List;

@EnableJpaRepositories
public interface OrdersRepository extends JpaRepository<Orders, Long> {
    @Query(value = "select max(o.orderNumber+1)  from Orders o")
    public Long max();

    @Query("Select o from Orders o where o.status = :status order by o.orderNumber asc ")
    List<Orders> getByStatus(OrderStatus status);

    @Query("Select o from Orders o join People p where p.email = :user order by o.orderNumber asc ")
    List<Orders> getByUser(String user);
}
