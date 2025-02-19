package com.mknieszner.hazelcastpoc;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import com.mknieszner.hazelcastpoc.jpa.Product;
import com.mknieszner.hazelcastpoc.jpa.ProductRepository;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    private final HazelcastInstance hazelcastInstance;

    public ProductService(ProductRepository productRepository, HazelcastInstance hazelcastInstance) {
        this.productRepository = productRepository;
        this.hazelcastInstance = hazelcastInstance;
    }

    @SneakyThrows
    @Cacheable(value = "productsCache", key = "'all'", unless = "#result.isEmpty()")
    public List<Product> getAllProducts() {
        List<Product> result = productRepository.findAll();
        log.info("Pobrano z DB: {}", result);
        return result;
    }

    @CacheEvict(value = "productsCache", key = "'all'")
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public IMap<Object, Object> getCacheContent() {
        return hazelcastInstance.getMap("productsCache");
    }

    public void invalidate() {
        hazelcastInstance.getMap("productsCache").clear();
    }
}
