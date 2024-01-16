package com.company.gamestore.controller;

import com.company.gamestore.model.Invoice;
import com.company.gamestore.repository.InvoiceRepository;
import com.company.gamestore.service.InvoiceServiceLayer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BillingControllerTest {

    @MockBean
    private InvoiceRepository invoiceRepo;

    @MockBean
    private InvoiceServiceLayer invoiceService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void initialize() {
        invoiceRepo.deleteAll();
    }

    @Test
    public void createInvoiceTest() throws Exception {
        Invoice partialData = new Invoice();
        partialData.setName("Customer A");
        partialData.setStreet("100 Market St");
        partialData.setCity("Techville");
        partialData.setState("TS");
        partialData.setZipcode("90010");
        partialData.setItemType("Games");
        partialData.setItemId(10);
        partialData.setQuantity(1);
    
        Invoice completedData = new Invoice();
        completedData.setInvoiceId(1); // Assuming this is auto-generated
        completedData.setName("Customer A");
        completedData.setStreet("100 Market St");
        completedData.setCity("Techville");
        completedData.setState("TS");
        completedData.setZipcode("90010");
        completedData.setItemType("Games");
        completedData.setItemId(10);
        completedData.setUnitPrice(new BigDecimal("59.99"));
        completedData.setQuantity(1);
        completedData.setSubtotal(new BigDecimal("59.99"));
        completedData.setTax(new BigDecimal("5.00"));
        completedData.setProcessingFee(new BigDecimal("1.99"));
        completedData.setTotal(new BigDecimal("66.98"));
    
        when(invoiceService.createInvoice(partialData)).thenReturn(completedData);
        ObjectMapper objectMapper = new ObjectMapper();
    
        mockMvc.perform(post("/invoice")
                        .content(objectMapper.writeValueAsString(partialData))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }
    
    @Test
    public void retrieveInvoiceByIdTest() throws Exception {
        mockMvc.perform(get("/invoice/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
    
    @Test
    public void retrieveInvoiceByNameTest() throws Exception {
        mockMvc.perform(get("/invoice/by-name")
                        .param("name", "Customer A")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
    
    @Test
    public void retrieveAllInvoicesTest() throws Exception {
        mockMvc.perform(get("/invoices")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
    
    @Test
    public void modifyInvoiceTest() throws Exception {
        Invoice invoice = new Invoice();
        invoice.setInvoiceId(1); // Assuming we are updating invoice with ID 1
        invoice.setName("Customer B");
        invoice.setStreet("101 Tech Ave");
        invoice.setCity("Innovate City");
        invoice.setState("IT");
        invoice.setZipcode("90011");
        invoice.setItemType("Consoles");
        invoice.setItemId(11);
        invoice.setUnitPrice(new BigDecimal("299.99"));
        invoice.setQuantity(2);
        invoice.setSubtotal(new BigDecimal("599.98"));
        invoice.setTax(new BigDecimal("50.00"));
        invoice.setProcessingFee(new BigDecimal("4.99"));
        invoice.setTotal(new BigDecimal("654.97"));
    
        when(invoiceRepository.existsById(anyInt())).thenReturn(true);
        when(invoiceRepository.save(any(Invoice.class))).thenReturn(invoice);
        ObjectMapper objectMapper = new ObjectMapper();
    
        mockMvc.perform(put("/invoice/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invoice)))
        .andDo(print())
        .andExpect(status().isNoContent());
    }
}
