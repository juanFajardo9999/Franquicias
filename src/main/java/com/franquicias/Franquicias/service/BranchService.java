package com.franquicias.Franquicias.service;

import com.franquicias.Franquicias.entity.Branch;
import com.franquicias.Franquicias.entity.Franchise;
import com.franquicias.Franquicias.entity.Product;
import com.franquicias.Franquicias.repository.BranchRepository;
import com.franquicias.Franquicias.repository.FranchiseRepository;
import com.franquicias.Franquicias.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Comparator;

@Service
public class BranchService {

    private final BranchRepository branchRepository;
    private final FranchiseRepository franchiseRepository;
    private final ProductRepository productRepository;

    public BranchService(BranchRepository branchRepository, FranchiseRepository franchiseRepository, ProductRepository productRepository) {
        this.branchRepository = branchRepository;
        this.franchiseRepository = franchiseRepository;
        this.productRepository = productRepository;
    }

    // Agregar una sucursal a una franquicia
    public Branch addBranchToFranchise(Long franchiseId, Branch branch) {
        Franchise franchise = franchiseRepository.findById(franchiseId)
                .orElseThrow(() -> new EntityNotFoundException("Franchise not found"));
        branch.setFranchise(franchise);
        return branchRepository.save(branch);
    }

    // Eliminar una sucursal
    public void deleteBranch(Long branchId) {
        branchRepository.deleteById(branchId);
    }

    // Actualizar el nombre de una sucursal
    public Branch updateBranchName(Long branchId, String newName) {
        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(() -> new EntityNotFoundException("Branch not found"));
        branch.setName(newName);
        return branchRepository.save(branch);
    }

    // Obtener el producto con más stock en una sucursal
    public Product getMaxStockProductInBranch(Long branchId) {
        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(() -> new EntityNotFoundException("Branch not found"));
        return branch.getProducts().stream()
                .max(Comparator.comparingInt(Product::getStock))
                .orElseThrow(() -> new EntityNotFoundException("No products found in branch"));
    }
}