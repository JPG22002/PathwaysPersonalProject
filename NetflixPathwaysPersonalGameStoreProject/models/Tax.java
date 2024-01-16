package com.company.gamestore.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "state_tax")
public class StateTax {

    @Id
    @Pattern(regexp = "[A-Z]{2}", message = "State abbreviation must be two uppercase letters")
    private String stateAbbreviation;

    @NotNull(message = "Tax rate required")
    @DecimalMin(value = "0.00", message = "Tax rate must be non-negative")
    private BigDecimal taxRate;

    public StateTax() {}

    public StateTax(String stateAbbreviation, BigDecimal taxRate) {
        this.stateAbbreviation = stateAbbreviation;
        this.taxRate = taxRate;
    }

    // Getters and Setters

    public String getStateAbbreviation() {
        return stateAbbreviation;
    }

    public void setStateAbbreviation(String stateAbbreviation) {
        this.stateAbbreviation = stateAbbreviation;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    // equals, hashCode, and toString methods

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StateTax)) return false;
        StateTax stateTax = (StateTax) o;
        return Objects.equals(stateAbbreviation, stateTax.stateAbbreviation) &&
               Objects.equals(taxRate, stateTax.taxRate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stateAbbreviation, taxRate);
    }

    @Override
    public String toString() {
        return "StateTax{" +
                "stateAbbreviation='" + stateAbbreviation + '\'' +
                ", taxRate=" + taxRate +
                '}';
    }
}
