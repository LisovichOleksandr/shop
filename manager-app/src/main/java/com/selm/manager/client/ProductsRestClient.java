package com.selm.manager.client;

import com.selm.manager.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductsRestClient {

    List<Product> findAllProduct(String filter);

    Product createProduct(String title, String details);

    Optional<Product> findProduct(int productId);

    void updateProduct(int productId, String title, String details);

    void deleteProduct(int productId);
}
