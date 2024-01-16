package com.company.gamestore.repository;

import com.company.gamestore.model.Tax;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StateTaxRepository extends JpaRepository<Tax, String> {
    Optional<Tax> findByStateCode(String stateCode);
}
