package com.franquicias.Franquicias.controller;

import com.franquicias.Franquicias.entity.Product;
import com.franquicias.Franquicias.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/branch/{branchId}")
    public ResponseEntity<Product> addProductToBranch(@PathVariable Long branchId, @RequestBody Product product) {
        try {
            Product createdProduct = productService.addProductToBranch(branchId, product);
            return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        try {
            productService.deleteProduct(productId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{productId}/stock")
    public ResponseEntity<Product> updateProductStock(@PathVariable Long productId, @RequestParam int newStock) {
        try {
            Product updatedProduct = productService.updateProductStock(productId, newStock);
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{productId}/name")
    public ResponseEntity<Product> updateProductName(@PathVariable Long productId, @RequestParam String newName) {
        try {
            Product updatedProduct = productService.updateProductName(productId, newName);
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
