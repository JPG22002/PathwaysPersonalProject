package com.company.gamestore.controller;

import com.company.gamestore.model.Console;
import com.company.gamestore.repository.ConsoleRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/console")
public class ConsoleManagementController {

    private final ConsoleRepository consoleRepo;

    public ConsoleManagementController(ConsoleRepository consoleRepo) {
        this.consoleRepo = consoleRepo;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Console> getConsoleById(@PathVariable int id) {
        return consoleRepo.findById(id)
                          .map(ResponseEntity::ok)
                          .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/byManufacturer")
    public ResponseEntity<List<Console>> getConsolesByManufacturer(@RequestParam String manufacturer) {
        return consoleRepo.findConsoleByManufacturer(manufacturer)
                          .map(ResponseEntity::ok)
                          .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/all")
    public List<Console> getAllConsoles() {
        return consoleRepo.findAll();
    }

    @PostMapping
    public ResponseEntity<Console> addNewConsole(@RequestBody @Valid Console console) {
        return ResponseEntity.status(HttpStatus.CREATED).body(consoleRepo.save(console));
    }

    @PutMapping
    public ResponseEntity<Void> updateConsole(@RequestBody @Valid Console console) {
        consoleRepo.save(console);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConsole(@PathVariable int id) {
        consoleRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
