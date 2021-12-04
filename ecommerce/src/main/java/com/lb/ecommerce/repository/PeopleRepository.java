package com.lb.ecommerce.repository;

import com.lb.ecommerce.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeopleRepository extends JpaRepository<Category, Long> {
}
