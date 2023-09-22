package com.pagin.demo;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/products",
produces = {
        MediaType.APPLICATION_XML_VALUE,
        MediaType.APPLICATION_JSON_VALUE})
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;

    }

    @GetMapping
    List<Product> getProducts(@RequestParam (required = false) String name) {
        if (name == null) {
            return productRepository.findAll();
        } else {
            return productRepository.findAllByName(name);
        }
    }

    @GetMapping("/{id}")
    ResponseEntity<Product> getProductById (@PathVariable Integer id) {
        return productRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/producer")
    ResponseEntity<Producer> getProducerByProductId (@PathVariable Integer id) {
        return productRepository.findById(id)
                .map(Product::getProducer)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
