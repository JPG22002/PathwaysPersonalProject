package com.company.gamestore.repository;

import com.company.gamestore.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BillingRepository extends JpaRepository<Invoice, Integer> {
    Optional<List<Invoice>> findByCustomerName(String customerName);
}
