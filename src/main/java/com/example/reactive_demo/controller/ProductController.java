package com.example.reactive_demo.controller;

import com.example.reactive_demo.model.Product;
import com.example.reactive_demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration; 
@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // Get all products
    @GetMapping
    public Flux<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // Get single product by ID
    @GetMapping("/{id}")
    public Mono<Product> getProductById(@PathVariable String id) {
        return productService.getProductById(id);
    }

    // Stream products continuously
    @GetMapping(value = "/stream", produces = "text/event-stream")
    public Flux<Product> streamProducts() {
        return productService.getAllProducts()
                .repeat() // optional: continuously repeat stream
                .delayElements(Duration.ofSeconds(2)); // simulate live stream delay
    }
}
