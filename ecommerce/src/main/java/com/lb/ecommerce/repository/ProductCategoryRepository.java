package com.lb.ecommerce.repository;

import com.lb.ecommerce.entity.ProductCategory;
import jdk.jfr.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
    boolean existsById(int id);
    boolean existsByName(String name);
    ProductCategory getById(int id);
}
