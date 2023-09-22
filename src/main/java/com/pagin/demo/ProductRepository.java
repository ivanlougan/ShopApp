package com.pagin.demo;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {

    private final List<Product> products = new ArrayList<>();

    public ProductRepository() {
        products.add(
                new Product(1, "Mlieko", 3.9,
                        new Producer("Mlieczarz",
                                new Address("Jajkowice", "32-090", "Gruba 31")))
        );
        products.add(
                new Product(2, "Buleczka", 1.2,
                        new Producer("Piekarz",
                                new Address("Zadupice", "42-099", "Dupa 14")))
        );
        products.add(
                new Product(3, "Kawusia", 4.5,
                        new Producer("Barista",
                                new Address("Spytkowice", "52-678", "Posh 29")))
        );
    }

    List<Product> findAll() {
        return products;
    }

    List<Product> findAllByName (String name) {
        return products.stream()
                .filter(p -> p.getName().equalsIgnoreCase(name))
                .toList();
    }

    Optional<Product> findById (int id) {
        if (id > products.size()) {
            return Optional.empty();
        } else {
            return Optional.of(products.get(id - 1));
        }
    }

}
