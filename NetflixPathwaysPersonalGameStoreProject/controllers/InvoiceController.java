package com.company.gamestore.controller;

import com.company.gamestore.model.Invoice;
import com.company.gamestore.repository.InvoiceRepository;
import com.company.gamestore.service.InvoiceServiceLayer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/invoice")
public class BillingController {

    private final InvoiceRepository invoiceRepository;
    private final InvoiceServiceLayer invoiceService;

    public BillingController(InvoiceRepository invoiceRepository, InvoiceServiceLayer invoiceService) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceService = invoiceService;
    }

    @PostMapping
    public ResponseEntity<Invoice> createInvoice(@RequestBody @Valid Invoice invoice) {
        Invoice newInvoice = invoiceService.createInvoice(invoice);
        return ResponseEntity.status(HttpStatus.CREATED).body(newInvoice);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Invoice> findInvoiceById(@PathVariable("id") int invoiceId) {
        return invoiceRepository.findById(invoiceId)
                                .map(ResponseEntity::ok)
                                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/all")
    public List<Invoice> listAllInvoices() {
        return invoiceRepository.findAll();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Invoice>> searchInvoicesByName(@RequestParam String name) {
        return invoiceRepository.findInvoiceByName(name)
                                .map(ResponseEntity::ok)
                                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateInvoice(@PathVariable("id") int invoiceId, @Valid @RequestBody Invoice invoice) {
        if (!invoiceRepository.existsById(invoiceId)) {
            return ResponseEntity.notFound().build();
        }
        invoice.setInvoiceId(invoiceId);
        invoiceRepository.save(invoice);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeInvoice(@PathVariable("id") int invoiceId) {
        if (!invoiceRepository.existsById(invoiceId)) {
            return ResponseEntity.notFound().build();
        }
        invoiceRepository.deleteById(invoiceId);
        return ResponseEntity.noContent().build();
    }
}
