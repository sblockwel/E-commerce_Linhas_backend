package com.lb.ecommerce.repository;

import com.lb.ecommerce.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductOrderRepository extends JpaRepository<Category, Long> {
}
