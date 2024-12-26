package com.selm.catalogue.service;

import com.selm.catalogue.entity.Product;
import com.selm.catalogue.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DefaultProductService implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Iterable<Product> findAllProduct(String filter) {
        if (filter != null && !filter.isBlank()){
           return this.productRepository.findAllByTitleLikeIgnoreCase(filter);
        } else {
            return this.productRepository.findAll();
        }
    }

    @Override
    public Optional<Product> findProduct(int productId) {
        return productRepository.findById(productId);
    }

    // Желательно любой метод которий вносит к.то изменения выполнять в транзакции.
    @Override
    @Transactional
    public Product createProduct(String title, String details) {
        return this.productRepository.save(new Product(null, title, details));
    }

    @Override
    @Transactional
    public void updateProduct(Integer id, String title, String details) {
        this.productRepository.findById(id)
                .ifPresentOrElse(product -> {
                    product.setTitle(title);
                    product.setDetails(details);
//      С применением @Transactional сохрание происходит автоматически, если транзакция не откативается
//                    this.productRepository.save(product);
                }, () -> {
                    throw new NoSuchElementException();
                });
    }

    @Override
    @Transactional
    public void deleteProduct(Integer id) {
        this.productRepository.deleteById(id);
    }
}
