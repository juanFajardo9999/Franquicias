package com.franquicias.Franquicias.repository;
import com.franquicias.Franquicias.entity.Franchise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FranchiseRepository extends JpaRepository<Franchise, Long> {
}