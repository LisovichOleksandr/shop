package com.selm.catalogue.repository;

import com.selm.catalogue.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<Product> findAll();

    Optional<Product> findById(Integer id);

    Product save(Product product);

    void deleteById(Integer id);
}
