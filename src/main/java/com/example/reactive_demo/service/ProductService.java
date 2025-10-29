package com.example.reactive_demo.service;

import com.example.reactive_demo.model.Product;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {

    private final Flux<Product> productFlux = Flux.just(
            new Product("1", "Laptop", 55000.0),
            new Product("2", "Phone", 25000.0),
            new Product("3", "Tablet", 30000.0)
    );

    public Flux<Product> getAllProducts() {
        return productFlux;
    }

    public Mono<Product> getProductById(String id) {
        return productFlux.filter(p -> p.getId().equals(id)).next();
    }
}
