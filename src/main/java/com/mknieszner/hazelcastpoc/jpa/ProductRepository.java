package com.mknieszner.hazelcastpoc.jpa;

import com.mknieszner.hazelcastpoc.jpa.JpaProductRepository;
import com.mknieszner.hazelcastpoc.jpa.Product;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductRepository {
    private final JpaProductRepository repository;

    public ProductRepository(JpaProductRepository repository) {
        this.repository = repository;
    }

    @SneakyThrows
    public List<Product> findAll() {
        return repository.findAll();
    }

    public Product save(Product product) {
        return repository.save(product);
    }
}
