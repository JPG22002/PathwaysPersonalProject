package com.company.gamestore.repository;

import com.company.gamestore.model.Fee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransactionFeeRepository extends JpaRepository<Fee, String> {
    Optional<Fee> findByProductType(String productType);
    void removeByProductType(String productType);
}
