package com.ronak.javarealworld.multithreading.lockapi.reentrantreadwrite;

/**
 * Immutable availability data that can be safely returned from the catalog.
 */
public record ProductAvailability(String sku, int availableUnits) {

    public ProductAvailability {
        if (sku == null || sku.isBlank()) {
            throw new IllegalArgumentException("SKU cannot be blank.");
        }
        if (availableUnits < 0) {
            throw new IllegalArgumentException("Available units cannot be negative.");
        }
    }
}
