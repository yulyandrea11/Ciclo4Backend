package com.usa.ciclo4.repository.crud;

import com.usa.ciclo4.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductCrudRepository extends MongoRepository<Product, String> {
    public List<Product> findByPrice(double price);
    public List<Product> findByDescriptionContainingIgnoreCase(String description);
}
