package com.lb.ecommerce.repository;

import com.lb.ecommerce.entity.People;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeopleRepository extends JpaRepository<People, Long> {
}
