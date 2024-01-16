package com.company.gamestore.repository;

import com.company.gamestore.model.Invoice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BillingRepositoryTest {

    @Autowired
    private InvoiceRepository billingRepo;

    @BeforeEach
    public void setup() {
        billingRepo.deleteAll();
    }

    // Test for adding and retrieving an invoice
    @Test
    public void addAndRetrieveInvoiceTest() {
        Invoice invoice = new Invoice();    

        invoice = billingRepo.save(invoice);
        Optional<Invoice> retrievedInvoice = billingRepo.findById(invoice.getInvoiceId());

        assertTrue(retrievedInvoice.isPresent());
        assertEquals(invoice, retrievedInvoice.get());
    }

    // Test for getting an invoice by ID
    @Test
    public void getInvoiceByIdTest() {
        Invoice invoice = new Invoice();
        invoice.setName("Alice Smith");
        invoice.setStreet("1234 Elm Street");
        invoice.setCity("Metropolis");
        invoice.setState("NY");
        invoice.setZipcode("12345");
        invoice.setItemType("Game");
        invoice.setItemId(10);
        invoice.setUnitPrice(new BigDecimal("59.99"));
        invoice.setQuantity(2);
        invoice.setSubtotal(new BigDecimal("119.98"));
        invoice.setTax(new BigDecimal("10.80"));
        invoice.setProcessingFee(new BigDecimal("1.49"));
        invoice.setTotal(new BigDecimal("132.27"));

        invoice = billingRepo.save(invoice);
        Optional<Invoice> foundInvoice = billingRepo.findById(invoice.getInvoiceId());

        assertTrue(foundInvoice.isPresent());
        assertEquals(invoice, foundInvoice.get());
    }

    // Test for getting invoices by name
    @Test
    public void getInvoicesByNameTest() {
        Invoice invoice = new Invoice();
        invoice.setName("Alice Smith");
        invoice.setStreet("1234 Elm Street");
        invoice.setCity("Metropolis");
        invoice.setState("NY");
        invoice.setZipcode("12345");
        invoice.setItemType("Game");
        invoice.setItemId(10);
        invoice.setUnitPrice(new BigDecimal("59.99"));
        invoice.setQuantity(2);
        invoice.setSubtotal(new BigDecimal("119.98"));
        invoice.setTax(new BigDecimal("10.80"));
        invoice.setProcessingFee(new BigDecimal("1.49"));
        invoice.setTotal(new BigDecimal("132.27"));

        invoice = billingRepo.save(invoice);
        List<Invoice> invoicesByName = billingRepo.findInvoiceByName(invoice.getName()).orElse(null);

        assertNotNull(invoicesByName);
        assertTrue(invoicesByName.contains(invoice));
    }

    // Test for getting all invoices
    @Test
    public void getAllInvoicesTest() {
        Invoice invoice1 = new Invoice();
        invoice.setName("Alice Smith");
        invoice.setStreet("1234 Elm Street");
        invoice.setCity("Metropolis");
        invoice.setState("NY");
        invoice.setZipcode("12345");
        invoice.setItemType("Game");
        invoice.setItemId(10);
        invoice.setUnitPrice(new BigDecimal("59.99"));
        invoice.setQuantity(2);
        invoice.setSubtotal(new BigDecimal("119.98"));
        invoice.setTax(new BigDecimal("10.80"));
        invoice.setProcessingFee(new BigDecimal("1.49"));
        invoice.setTotal(new BigDecimal("132.27"));
    
        Invoice invoice2 = new Invoice();
        // Set properties for invoice2
        // ...
        invoice2.setName("Bob Johnson");
        invoice2.setStreet("5678 Maple Avenue");
        invoice2.setCity("Springfield");
        invoice2.setState("IL");
        invoice2.setZipcode("67890");
        invoice2.setItemType("Console");
        invoice2.setItemId(5);
        invoice2.setUnitPrice(new BigDecimal("299.99"));
        invoice2.setQuantity(1);
        invoice2.setSubtotal(new BigDecimal("299.99"));
        invoice2.setTax(new BigDecimal("27.00"));
        invoice2.setProcessingFee(new
        BigDecimal("15.49"));
        invoice2.setTotal(new BigDecimal("342.48"));
    
        billingRepo.save(invoice1);
        billingRepo.save(invoice2);
        List<Invoice> allInvoices = billingRepo.findAll();
    
        assertEquals(2, allInvoices.size());
        assertTrue(allInvoices.contains(invoice1));
        assertTrue(allInvoices.contains(invoice2));
    }
    
    // Test for updating an invoice
    @Test
    public void updateInvoiceTest() {
        Invoice invoice = new Invoice();
        invoice.setName("Alice Smith");
        invoice.setStreet("1234 Elm Street");
        invoice.setCity("Metropolis");
        invoice.setState("NY");
        invoice.setZipcode("12345");
        invoice.setItemType("Game");
        invoice.setItemId(10);
        invoice.setUnitPrice(new BigDecimal("59.99"));
        invoice.setQuantity(2);
        invoice.setSubtotal(new BigDecimal("119.98"));
        invoice.setTax(new BigDecimal("10.80"));
        invoice.setProcessingFee(new BigDecimal("1.49"));
        invoice.setTotal(new BigDecimal("132.27"));
    
        invoice = billingRepo.save(invoice);
    
        // Update properties for invoice
    
        invoice.setName("Alice Johnson"); // Updating the name
        invoice.setTotal(new BigDecimal("200.00")); // Updating the total
    
        Invoice updatedInvoice = billingRepo.save(invoice);
        Optional<Invoice> retrievedInvoice = billingRepo.findById(invoice.getInvoiceId());
    
        assertTrue(retrievedInvoice.isPresent());
        assertEquals(updatedInvoice, retrievedInvoice.get());
    }
    
    // Test for deleting an invoice by ID
    @Test
    public void deleteInvoiceByIdTest() {
        Invoice invoice = new Invoice();

        invoice.setName("Alice Smith");
        invoice.setStreet("1234 Elm Street");
        invoice.setCity("Metropolis");
        invoice.setState("NY");
        invoice.setZipcode("12345");
        invoice.setItemType("Game");
        invoice.setItemId(10);
        invoice.setUnitPrice(new BigDecimal("59.99"));
        invoice.setQuantity(2);
        invoice.setSubtotal(new BigDecimal("119.98"));
        invoice.setTax(new BigDecimal("10.80"));
        invoice.setProcessingFee(new BigDecimal("1.49"));
        invoice.setTotal(new BigDecimal("132.27"));
    
        invoice = billingRepo.save(invoice);
        billingRepo.deleteById(invoice.getInvoiceId());
        Optional<Invoice> retrievedInvoice = billingRepo.findById(invoice.getInvoiceId());
    
        assertFalse(retrievedInvoice.isPresent());
    }
}    