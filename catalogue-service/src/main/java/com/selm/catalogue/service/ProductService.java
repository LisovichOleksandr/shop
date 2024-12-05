package com.selm.catalogue.service;

import com.selm.catalogue.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findAllProduct();

    Optional<Product> findProduct(int id);

    Product createProduct(String title, String details);

    void updateProduct(Integer id, String title, String details);

    void deleteProduct(Integer id);
}