package com.lb.ecommerce.repository;

import com.lb.ecommerce.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Category, Long> {
}
