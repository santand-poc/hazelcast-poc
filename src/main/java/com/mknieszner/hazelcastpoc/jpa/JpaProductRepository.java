package com.mknieszner.hazelcastpoc.jpa;


import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaProductRepository extends JpaRepository<Product, Long> {
}
