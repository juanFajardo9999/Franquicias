package com.franquicias.Franquicias.repository;

import com.franquicias.Franquicias.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
