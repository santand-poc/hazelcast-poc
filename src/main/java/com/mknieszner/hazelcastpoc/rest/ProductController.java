package com.mknieszner.hazelcastpoc.rest;

import com.hazelcast.map.IMap;
import com.mknieszner.hazelcastpoc.ProductService;
import com.mknieszner.hazelcastpoc.jpa.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
@Slf4j
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public List<Product> getAllProducts() {
        long start = System.currentTimeMillis();
        List<Product> result = productService.getAllProducts();
        long duration = System.currentTimeMillis() - start;
        log.info("Czas pobrania: " + duration + " ms");
        return result;
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        long start = System.currentTimeMillis();
        Product result = productService.getProductById(id);
        long duration = System.currentTimeMillis() - start;
        log.info("Czas pobrania: " + duration + " ms");
        return result;
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @PutMapping
    public Product updateProduct(@RequestBody Product product) {
        return productService.updateProduct(product.getId(), product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    @GetMapping("/cache")
    public IMap<Object, Object> getCacheContent() {
        return productService.getCacheContent();
    }

    @DeleteMapping("/cache")
    public void deleteCache() {
        productService.invalidateCache();
    }

    @GetMapping("/cache-cluster-info")
    public Map<String, Object> getCacheClusterInfo() {
        return productService.getCacheClusterInfo();
    }
}
