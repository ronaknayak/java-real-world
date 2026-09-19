package com.ronak.javarealworld.java8.functionalinterface;

import java.math.BigDecimal;
import java.util.Objects;

/** Small domain object used by the product validation example. */
public record Product(String sku, String name, BigDecimal price) {
    public Product {
        Objects.requireNonNull(sku, "sku must not be null");
        Objects.requireNonNull(name, "name must not be null");
        Objects.requireNonNull(price, "price must not be null");
        if (sku.isBlank() || name.isBlank()) throw new IllegalArgumentException("sku and name must not be blank");
    }
}