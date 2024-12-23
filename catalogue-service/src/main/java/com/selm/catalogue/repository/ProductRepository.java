package com.selm.catalogue.repository;

import com.selm.catalogue.entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {

}
