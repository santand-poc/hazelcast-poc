package com.mknieszner.hazelcastpoc.rest;

import com.hazelcast.map.IMap;
import com.mknieszner.hazelcastpoc.ProductService;
import com.mknieszner.hazelcastpoc.jpa.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/products")
@Slf4j
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        long start = System.currentTimeMillis();
        List<Product> result =  productService.getAllProducts();
        long duration = System.currentTimeMillis() - start;
        log.info("Czas pobrania: " + duration + " ms");
        return result;
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @GetMapping("/cache")
    public IMap<Object, Object> getCacheContent() {
        return productService.getCacheContent();
    }
    @DeleteMapping("/cache")
    public void deleteCache() {
        productService.invalidate();
    }
}
