package com.company.gamestore.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "customer_invoice")
public class CustomerInvoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Customer name required")
    private String customerName;

    @NotBlank(message = "Street address required")
    private String streetAddress;

    @NotBlank(message = "City required")
    private String city;

    @NotBlank(message = "State required")
    private String state;

    @NotBlank(message = "Zip code required")
    private String zipCode;

    @NotBlank(message = "Product type required")
    private String productType;

    @NotNull(message = "Product ID required")
    private Integer productId;

    @NotNull(message = "Unit price required")
    private BigDecimal pricePerUnit;

    @NotNull(message = "Quantity required")
    @Min(value = 1, message = "At least 1 item required")
    private Integer quantity;

    private BigDecimal subtotal;
    private BigDecimal tax;
    private BigDecimal processingFee;
    private BigDecimal total;

    public CustomerInvoice() {}

    public CustomerInvoice(String customerName, String city, String state, String zipCode, 
                           String productType, Integer productId, Integer quantity) {
        this.customerName = customerName;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.productType = productType;
        this.productId = productId;
        this.quantity = quantity;
    }

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public BigDecimal getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(BigDecimal pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getProcessingFee() {
        return processingFee;
    }

    public void setProcessingFee(BigDecimal processingFee) {
        this.processingFee = processingFee;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    // equals, hashCode, and toString methods

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomerInvoice)) return false;
        CustomerInvoice that = (CustomerInvoice) o;
        return Objects.equals(id, that.id) &&
               Objects.equals(customerName, that.customerName) &&
               Objects.equals(streetAddress, that.streetAddress) &&
               Objects.equals(city, that.city) &&
               Objects.equals(state, that.state) &&
               Objects.equals(zipCode, that.zipCode) &&
               Objects.equals(productType, that.productType) &&
               Objects.equals(productId, that.productId) &&
               Objects.equals(pricePerUnit, that.pricePerUnit) &&
               Objects.equals(quantity, that.quantity) &&
               Objects.equals(subtotal, that.subtotal) &&
               Objects.equals(tax, that.tax) &&
               Objects.equals(processingFee, that.processingFee) &&
               Objects.equals(total, that.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerName, streetAddress, city, state, zipCode, 
                            productType, productId, pricePerUnit, quantity, 
                            subtotal, tax, processingFee, total);
    }

    @Override
    public String toString() {
        return "CustomerInvoice{" +
                "id=" + id +
                ", customerName='" + customerName + '\'' +
                ", streetAddress='" + streetAddress + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", productType='" + productType + '\'' +
                ", productId=" + productId +
                ", pricePerUnit=" + pricePerUnit +
                ", quantity=" + quantity +
                ", subtotal=" + subtotal +
                ", tax=" + tax +
                ", processingFee=" + processingFee +
                ", total=" + total +
                '}';
    }
}
