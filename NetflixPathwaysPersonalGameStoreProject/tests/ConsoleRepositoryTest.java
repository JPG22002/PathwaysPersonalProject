package com.company.gamestore.repository;

import com.company.gamestore.model.Console;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class GamingConsoleRepositoryTest {

    @Autowired
    private ConsoleRepository consoleRepo;

    @BeforeEach
    public void setup() {
        consoleRepo.deleteAll();
    }

    // Test for adding a console
    @Test
    public void addAndRetrieveConsoleTest() {
        Console console = new Console();
        console.setModel("Model X");
        console.setManufacturer("Make Y");
        console.setMemoryAmount("8GB");
        console.setProcessor("XYZ");
        console.setPrice(new BigDecimal("299.99"));

        console = consoleRepo.save(console);
        Optional<Console> retrievedConsole = consoleRepo.findById(console.getConsoleId());

        assertTrue(retrievedConsole.isPresent());
        assertEquals(console, retrievedConsole.get());
    }

    // Test for retrieving a console by ID
    @Test
    public void getConsoleByIdTest() {
        Console console = new Console();
        console.setModel("Model Z");
        console.setManufacturer("Make A");
        console.setMemoryAmount("16GB");
        console.setProcessor("ABC");
        console.setPrice(new BigDecimal("399.99"));

        console = consoleRepo.save(console);
        Optional<Console> retrievedConsole = consoleRepo.findById(console.getConsoleId());

        assertTrue(retrievedConsole.isPresent());
        assertEquals(console, retrievedConsole.get());
    }

    // Test for retrieving consoles by manufacturer
    @Test
    public void getConsolesByManufacturerTest() {
        Console console = new Console();
        console.setModel("Model M");
        console.setManufacturer("Make B");
        console.setMemoryAmount("32GB");
        console.setProcessor("DEF");
        console.setPrice(new BigDecimal("499.99"));

        console = consoleRepo.save(console);
        List<Console> consolesByManufacturer = consoleRepo.findConsoleByManufacturer(console.getManufacturer()).orElse(null);

        assertNotNull(consolesByManufacturer);
        assertTrue(consolesByManufacturer.contains(console));
    }

    // Test for retrieving all consoles
    @Test
    public void getAllConsolesTest() {
        Console console1 = new Console();
        console1.setModel("Model C");
        console1.setManufacturer("Make C");
        console1.setMemoryAmount("4GB");
        console1.setProcessor("GHI");
        console1.setPrice(new BigDecimal("199.99"));

        Console console2 = new Console();
        console2.setModel("Model D");
        console2.setManufacturer("Make D");
        console2.setMemoryAmount("12GB");
        console2.setProcessor("JKL");
        console2.setPrice(new BigDecimal("349.99"));

        consoleRepo.save(console1);
        consoleRepo.save(console2);
        List<Console> allConsoles = consoleRepo.findAll();

        assertTrue(allConsoles.contains(console1));
        assertTrue(allConsoles.contains(console2));
    }
// Test for updating a console
@Test
public void updateConsoleTest() {
    Console console = new Console();
    console.setModel("Model U");
    console.setManufacturer("Make U");
    console.setMemoryAmount("8GB");
    console.setProcessor("MNO");
    console.setPrice(new BigDecimal("299.99"));

    console = consoleRepo.save(console);
    console.setModel("Updated Model U");
    console.setMemoryAmount("16GB");
    console.setProcessor("Updated MNO");

    Console updatedConsole = consoleRepo.save(console);
    Optional<Console> retrievedConsole = consoleRepo.findById(console.getConsoleId());

    assertTrue(retrievedConsole.isPresent());
    assertEquals(updatedConsole, retrievedConsole.get());
}

// Test for deleting a console by ID
@Test
public void deleteConsoleByIdTest() {
    Console console = new Console();
    console.setModel("Model D");
    console.setManufacturer("Make D");
    console.setMemoryAmount("8GB");
    console.setProcessor("PQR");
    console.setPrice(new BigDecimal("259.99"));

    console = consoleRepo.save(console);
    consoleRepo.deleteById(console.getConsoleId());
    Optional<Console> retrievedConsole = consoleRepo.findById(console.getConsoleId());

    assertFalse(retrievedConsole.isPresent());
    }
}