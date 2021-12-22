package com.usa.ciclo4.service;

import com.usa.ciclo4.model.Product;
import com.usa.ciclo4.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAll(){
        return productRepository.getAll();
    }

    public Optional<Product> getProduct(String reference){
        return productRepository.getProduct(reference);
    }

    public Product save(Product product){
        if (product.getReference()== null){
            return product;
        }
        return productRepository.save(product);
    }

    public Product update(Product product){
        if (product.getReference() != null) {
            Optional<Product> productAux = productRepository.getProduct(product.getReference());
            if (!productAux.isEmpty()) {
                if (product.getBrand() != null) {
                    productAux.get().setBrand(product.getBrand());
                }
                if (product.getCategory() != null) {
                    productAux.get().setCategory(product.getCategory());
                }
                if (product.getDescription() != null) {
                    productAux.get().setDescription(product.getDescription());
                }
                if (product.getNombre() != null) {
                    productAux.get().setNombre(product.getNombre());
                }
                if (product.getPrice() != 0.0) {
                    productAux.get().setPrice(product.getPrice());
                }
                if (product.getQuantity() != 0) {
                    productAux.get().setQuantity(product.getQuantity());
                }
                if (product.getPhotography() != null) {
                    productAux.get().setPhotography(product.getPhotography());
                }
                productAux.get().setAvailability(product.isAvailability());
                productRepository.update(productAux.get());
                return productAux.get();
            } else {
                return product;
            }
        }else{
            return product;
        }
    }

    public boolean delete(String reference){
        return getProduct(reference).map(product -> {
            productRepository.delete(product);
            return true;
        }).orElse(false);
    }

    public List<Product> getByPrice(double price){
        return productRepository.getByPrice(price);
    }

    public List<Product> getByDescriptionContains(String description){
        return productRepository.getByDescriptionContains(description);
    }
}
