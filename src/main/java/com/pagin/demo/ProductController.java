package com.pagin.demo;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    Product getProductById (@PathVariable Integer id) {
        return productRepository.findById(id);
    }

    @GetMapping("/{id}/producer")
    Producer getProducerByProductId (@PathVariable Integer id) {
        return productRepository.findById(id).getProducer();
    }
}
