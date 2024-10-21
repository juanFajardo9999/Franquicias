package com.franquicias.Franquicias.service;

import com.franquicias.Franquicias.entity.Branch;
import com.franquicias.Franquicias.entity.Product;
import com.franquicias.Franquicias.repository.BranchRepository;
import com.franquicias.Franquicias.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final BranchRepository branchRepository;

    public ProductService(ProductRepository productRepository, BranchRepository branchRepository) {
        this.productRepository = productRepository;
        this.branchRepository = branchRepository;
    }

    // Agregar un producto a una sucursal
    public Product addProductToBranch(Long branchId, Product product) {
        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(() -> new EntityNotFoundException("Branch not found"));
        product.setBranch(branch);
        return productRepository.save(product);
    }

    // Eliminar un producto
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    // Modificar el stock de un producto
    public Product updateProductStock(Long productId, int newStock) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));
        product.setStock(newStock);
        return productRepository.save(product);
    }

    // Actualizar el nombre de un producto
    public Product updateProductName(Long productId, String newName) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));
        product.setName(newName);
        return productRepository.save(product);
    }
}

