package com.franquicias.Franquicias.controller;

import com.franquicias.Franquicias.entity.Franchise;
import com.franquicias.Franquicias.service.FranchiseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/franchises")
public class FranchiseController {

    private final FranchiseService franchiseService;

    public FranchiseController(FranchiseService franchiseService) {
        this.franchiseService = franchiseService;
    }

    @GetMapping
    public ResponseEntity<List<Franchise>> getAllFranchises() {
        List<Franchise> franchises = franchiseService.findAll();
        return new ResponseEntity<>(franchises, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Franchise> createFranchise(@RequestBody Franchise franchise) {
        try {
            Franchise createdFranchise = franchiseService.save(franchise);
            return new ResponseEntity<>(createdFranchise, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFranchise(@PathVariable Long id) {
        try {
            franchiseService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Franchise> updateFranchiseName(@PathVariable Long id, @RequestParam String name) {
        try {
            Franchise updatedFranchise = franchiseService.updateName(id, name);
            return new ResponseEntity<>(updatedFranchise, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
