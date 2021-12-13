package com.lb.ecommerce.repository;

import com.lb.ecommerce.entity.Order;
import com.lb.ecommerce.models.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

@EnableJpaRepositories
public interface OrdersRepository extends JpaRepository<Order, Long> {
    @Query(value = "select max(o.orderNumber+1)  from Order o")
    public Long max();

    @Query("Select o from Order o where o.status = :status order by o.orderNumber asc ")
    List<Order> getByStatus(OrderStatus status);

    @Query("Select o from Order o join o.person p where p.email = :user order by o.orderNumber asc ")
    List<Order> getByUser(String user);
}
