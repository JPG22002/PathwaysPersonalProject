package com.company.gamestore.repository;

import com.company.gamestore.model.Fee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TransactionFeeRepositoryTest {

    @Autowired
    FeeRepository transactionFeeRepo;

    @BeforeEach
    public void setup() {
        transactionFeeRepo.deleteAll();
    }

    // Test for adding a fee
    @Test
    public void addAndRetrieveFeeTest() {
        Fee fee = new Fee();
        fee.setProductType("Accessory");
        fee.setFee(BigDecimal.valueOf(5.99));

        transactionFeeRepo.save(fee);
        Optional<Fee> retrievedFee = transactionFeeRepo.findFeeByProductType(fee.getProductType());

        assertTrue(retrievedFee.isPresent());
        assertEquals(retrievedFee.get(), fee);
    }

    // Test for getting fee by product type
    @Test
    public void getFeeByProductTypeTest() {
        Fee fee = new Fee();
        fee.setProductType("Hardware");
        fee.setFee(BigDecimal.valueOf(19.99));

        transactionFeeRepo.save(fee);
        Optional<Fee> foundFee = transactionFeeRepo.findFeeByProductType(fee.getProductType());

        assertTrue(foundFee.isPresent());
        assertEquals(foundFee.get(), fee);
    }

    // Test for getting all fees
    @Test
    public void getAllFeesTest(){Fee fee1 = new Fee();
        fee1.setProductType("Console");
        fee1.setFee(BigDecimal.valueOf(10.99));
        transactionFeeRepo.save(fee1);
        Fee fee2 = new Fee();
    fee2.setProductType("Controller");
    fee2.setFee(BigDecimal.valueOf(3.99));
    transactionFeeRepo.save(fee2);

    List<Fee> fees = transactionFeeRepo.findAll();

    assertTrue(fees.contains(fee1));
    assertTrue(fees.contains(fee2));
    assertEquals(2, fees.size());
}

// Test for updating a fee
@Test
public void updateFeeTest() {
    Fee fee = new Fee();
    fee.setProductType("Game");
    fee.setFee(BigDecimal.valueOf(14.99));
    transactionFeeRepo.save(fee);

    fee.setFee(BigDecimal.valueOf(9.99));
    transactionFeeRepo.save(fee);

    Optional<Fee> updatedFee = transactionFeeRepo.findFeeByProductType(fee.getProductType());

    assertTrue(updatedFee.isPresent());
    assertEquals(updatedFee.get(), fee);
}

// Test for deleting a fee by product type
@Test
public void deleteFeeByProductTypeTest() {
    Fee fee = new Fee();
    fee.setProductType("Game");
    fee.setFee(BigDecimal.valueOf(14.99));

    fee = transactionFeeRepo.save(fee);

    Optional<Fee> foundFee = transactionFeeRepo.findFeeByProductType(fee.getProductType());

    assertTrue(foundFee.isPresent());
    assertEquals(foundFee.get(), fee);

    transactionFeeRepo.deleteById(foundFee.get().getProductType());

    foundFee = transactionFeeRepo.findFeeByProductType(fee.getProductType());

    assertFalse(foundFee.isPresent());
    }
}
