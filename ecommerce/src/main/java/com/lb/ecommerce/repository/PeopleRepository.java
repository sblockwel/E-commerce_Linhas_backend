package com.lb.ecommerce.repository;

import com.lb.ecommerce.entity.People;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface PeopleRepository extends JpaRepository<People, Long> {

    @Query("Select p from People p where p.type = :type order by p.name asc ")
    List<People> getByType(char type);

}
