package com.lb.ecommerce.repository;

import com.lb.ecommerce.entity.Person;
import com.lb.ecommerce.models.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

    @Query("SELECT p FROM Person p WHERE p.userRole = :userRole")
    List<Person> getAllByRole(UserRole userRole);
}
