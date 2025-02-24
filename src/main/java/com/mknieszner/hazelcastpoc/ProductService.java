package com.mknieszner.hazelcastpoc;

import com.hazelcast.map.IMap;
import com.mknieszner.hazelcastpoc.cache.CacheInstance;
import com.mknieszner.hazelcastpoc.jpa.JpaProductRepository;
import com.mknieszner.hazelcastpoc.jpa.Product;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import static java.lang.String.format;

@Service
@Slf4j
@RequiredArgsConstructor
@CacheConfig(cacheNames = "#{@productCacheName}")
public class ProductService {
    private final JpaProductRepository productRepository;
    private final CacheInstance cacheInstance;
    private final String productCacheName;

    @Cacheable(key = "'all'", unless = "#result.isEmpty()")
    public List<Product> getAllProducts() {
        List<Product> result = productRepository.findAll();
        log.info("Fetched from DB: {} product(s)", result.size());
        return result;
    }

    @Cacheable(key = "#id.toString()", unless = "#result == null")
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(format("Product with id = %d not found", id)));
    }

    @Transactional
    @CacheEvict(key = "'all'", beforeInvocation = true)
    public Product saveProduct(Product product) {
        Product savedProduct = productRepository.save(product);

        cacheInstance.registerCacheUpdateAfterCommit(savedProduct.getId().toString(), savedProduct, productCacheName);

        log.info("Saved product ID = {} and removed cache.", savedProduct.getId());
        return savedProduct;
    }

    @Transactional
    @Caching(evict = {
            @CacheEvict(key = "'all'", beforeInvocation = true),
            @CacheEvict(key = "#id.toString()", beforeInvocation = true)
    })
    public Product updateProduct(Long id, Product product) {
        if (!productRepository.existsById(id)) {
            throw new EntityNotFoundException("Product with ID " + id + " does not exists.");
        }

        Product updatedProduct = productRepository.save(product);

        cacheInstance.registerCacheUpdateAfterCommit(product.getId().toString(), updatedProduct, productCacheName);

        log.info("Product ID = {} updated in DB and i IN CACHE after commit.", updatedProduct.getId());
        return updatedProduct;
    }

    @Transactional
    @Caching(evict = {
            @CacheEvict(key = "'all'", beforeInvocation = true),
            @CacheEvict(key = "#id.toString()", beforeInvocation = true)
    })
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);

        log.info("Product with ID = {} removed if existed and cache evicted after commit.", id);
    }

    public IMap<Object, Object> getCacheContent() {
        return cacheInstance.getMap(productCacheName);
    }

    public void invalidateCache() {
        cacheInstance.getMap(productCacheName).clear();
        log.info("Cache {} evicted.", productCacheName);
    }

    public Map<String, Object> getCacheClusterInfo() {
        return cacheInstance.getClusterInfo();
    }
}
