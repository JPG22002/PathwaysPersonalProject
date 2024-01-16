package com.company.gamestore.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "transaction_fee")
public class TransactionFee {

    @Id
    @NotBlank(message = "Type of product required")
    @Size(max = 50, message = "Max 50 characters for product type")
    private String type;

    @NotNull(message = "Fee value required")
    @DecimalMin(value = "0.00", inclusive = true, message = "Fee must be non-negative")
    private BigDecimal amount;

    public TransactionFee() {}

    public TransactionFee(String type, BigDecimal amount) {
        this.type = type;
        this.amount = amount;
    }

    // Getters and Setters

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    // equals, hashCode, and toString methods

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TransactionFee)) return false;
        TransactionFee that = (TransactionFee) o;
        return Objects.equals(type, that.type) && 
               Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, amount);
    }

    @Override
    public String toString() {
        return "TransactionFee{" +
                "type='" + type + '\'' +
                ", amount=" + amount +
                '}';
    }
}
