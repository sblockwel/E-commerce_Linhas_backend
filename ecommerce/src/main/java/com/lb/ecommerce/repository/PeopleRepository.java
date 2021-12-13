package com.lb.ecommerce.repository;

import com.lb.ecommerce.entity.People;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface PeopleRepository extends JpaRepository<People, Long> {
    Optional<People> findByEmail(String email);

    @Transactional
    @Modifying
    @Query("UPDATE People a " +
            "SET a.enabled = TRUE WHERE a.email = ?1")
    int enablePeople(String email);

}
