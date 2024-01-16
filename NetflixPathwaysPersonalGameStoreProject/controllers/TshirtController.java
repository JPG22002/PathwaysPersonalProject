package com.company.gamestore.controller;

import com.company.gamestore.model.Tshirt;
import com.company.gamestore.repository.TshirtRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/tshirt")
public class ApparelController {

    private final TshirtRepository tshirtRepo;

    public ApparelController(TshirtRepository tshirtRepo) {
        this.tshirtRepo = tshirtRepo;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tshirt> findTshirtById(@PathVariable int id) {
        return tshirtRepo.findById(id)
                         .map(ResponseEntity::ok)
                         .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/color/{color}")
    public ResponseEntity<List<Tshirt>> findTshirtsByColor(@PathVariable String color) {
        return tshirtRepo.findByColor(color)
                         .map(ResponseEntity::ok)
                         .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/size/{size}")
    public ResponseEntity<List<Tshirt>> findTshirtsBySize(@PathVariable String size) {
        return tshirtRepo.findBySize(size)
                         .map(ResponseEntity::ok)
                         .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/all")
    public List<Tshirt> listAllTshirts() {
        return tshirtRepo.findAll();
    }

    @PostMapping
    public ResponseEntity<Tshirt> addTshirt(@Valid @RequestBody Tshirt tshirt) {
        Tshirt savedTshirt = tshirtRepo.save(tshirt);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTshirt);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTshirt(@PathVariable int id, @Valid @RequestBody Tshirt tshirt) {
        if (!tshirtRepo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        tshirt.setId(id);
        tshirtRepo.save(tshirt);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTshirt(@PathVariable int id) {
        if (!tshirtRepo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        tshirtRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
