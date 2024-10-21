package com.franquicias.Franquicias.controller;

import com.franquicias.Franquicias.entity.Branch;
import com.franquicias.Franquicias.entity.Product;
import com.franquicias.Franquicias.service.BranchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/branches")
public class BranchController {

    private final BranchService branchService;

    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    @PostMapping("/franchise/{franchiseId}")
    public ResponseEntity<Branch> addBranchToFranchise(@PathVariable Long franchiseId, @RequestBody Branch branch) {
        try {
            Branch createdBranch = branchService.addBranchToFranchise(franchiseId, branch);
            return new ResponseEntity<>(createdBranch, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{branchId}")
    public ResponseEntity<Void> deleteBranch(@PathVariable Long branchId) {
        try {
            branchService.deleteBranch(branchId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{branchId}/name")
    public ResponseEntity<Branch> updateBranchName(@PathVariable Long branchId, @RequestParam String newName) {
        try {
            Branch updatedBranch = branchService.updateBranchName(branchId, newName);
            return new ResponseEntity<>(updatedBranch, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{branchId}/max-stock-product")
    public ResponseEntity<Product> getMaxStockProductInBranch(@PathVariable Long branchId) {
        try {
            Product product = branchService.getMaxStockProductInBranch(branchId);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
