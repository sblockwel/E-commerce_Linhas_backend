package com.lb.ecommerce.repository;

import com.lb.ecommerce.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface PeopleRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByEmail(String email);

    @Transactional
    @Modifying
    @Query("UPDATE Person a " +
            "SET a.enabled = TRUE WHERE a.email = ?1")
    int enablePeople(String email);

}
