package com.franquicias.Franquicias.service;

import com.franquicias.Franquicias.entity.Franchise;
import com.franquicias.Franquicias.repository.FranchiseRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FranchiseService {

    private final FranchiseRepository franchiseRepository;

    public FranchiseService(FranchiseRepository franchiseRepository) {
        this.franchiseRepository = franchiseRepository;
    }

    public List<Franchise> findAll() {
        return franchiseRepository.findAll();
    }

    public Franchise save(Franchise franchise) {
        return franchiseRepository.save(franchise);
    }

    public void delete(Long id) {
        franchiseRepository.deleteById(id);
    }

    public Franchise updateName(Long id, String name) {
        Franchise franchise = franchiseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Franchise not found"));
        franchise.setName(name);
        return franchiseRepository.save(franchise);
    }
}
