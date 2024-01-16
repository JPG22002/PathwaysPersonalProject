package com.company.gamestore.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "consoles")
public class GamingConsole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Model required")
    @Size(max = 50, message = "Max 50 characters for model")
    private String model;

    @NotBlank(message = "Manufacturer required")
    @Size(max = 50, message = "Max 50 characters for manufacturer")
    private String manufacturer;

    @Size(max = 20, message = "Max 20 characters for memory amount")
    private String memoryAmount;

    @Size(max = 20, message = "Max 20 characters for processor")
    private String processor;

    @NotNull(message = "Price required")
    @Digits(integer = 3, fraction = 2, message = "Max 3 digits before and 2 digits after decimal for price")
    private BigDecimal price;

    @Min(value = 0, message = "Quantity must be non-negative")
    private Integer quantity;

    public GamingConsole() {}

    public GamingConsole(Integer id, String model, String manufacturer, String memoryAmount, String processor, BigDecimal price, Integer quantity) {
        this.id = id;
        this.model = model;
        this.manufacturer = manufacturer;
        this.memoryAmount = memoryAmount;
        this.processor = processor;
        this.price = price;
        this.quantity = quantity;
    }

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    // Other getters and setters remain unchanged

    // toString, equals, and hashCode methods

    @Override
    public String toString() {
        return "GamingConsole{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", memoryAmount='" + memoryAmount + '\'' +
                ", processor='" + processor + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GamingConsole)) return false;
        GamingConsole that = (GamingConsole) o;
        return Objects.equals(id, that.id) && 
               Objects.equals(model, that.model) &&
               Objects.equals(manufacturer, that.manufacturer) &&
               Objects.equals(memoryAmount, that.memoryAmount) &&
               Objects.equals(processor, that.processor) &&
               Objects.equals(price, that.price) &&
               Objects.equals(quantity, that.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, model, manufacturer, memoryAmount, processor, price, quantity);
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getMemoryAmount() {
        return memoryAmount;
    }

    public void setMemoryAmount(String memoryAmount) {
        this.memoryAmount = memoryAmount;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
