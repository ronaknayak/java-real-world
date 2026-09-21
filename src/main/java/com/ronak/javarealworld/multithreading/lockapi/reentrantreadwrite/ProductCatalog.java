package com.ronak.javarealworld.multithreading.lockapi.reentrantreadwrite;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * A read-heavy in-memory catalog. Many pricing or availability requests can
 * read concurrently, while refreshes obtain exclusive access.
 */
public final class ProductCatalog {

    private final ReentrantReadWriteLock catalogLock = new ReentrantReadWriteLock(true);
    private final Map<String, ProductAvailability> products = new LinkedHashMap<>();

    public ProductCatalog(Map<String, ProductAvailability> initialProducts) {
        replaceCatalog(initialProducts);
    }

    public Optional<ProductAvailability> findBySku(String sku) {
        validateSku(sku);
        ReentrantReadWriteLock.ReadLock readLock = catalogLock.readLock();
        readLock.lock();

        try {
            System.out.println(Thread.currentThread().getName() + " acquired the read lock.");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        try {
            return Optional.ofNullable(products.get(sku));
        } finally {
            readLock.unlock();
        }
    }

    /**
     * Returns an immutable snapshot so callers cannot mutate catalog state
     * after the read lock has been released.
     */
    public Map<String, ProductAvailability> snapshot() {
        ReentrantReadWriteLock.ReadLock readLock = catalogLock.readLock();
        readLock.lock();
        try {
            return Map.copyOf(products);
        } finally {
            readLock.unlock();
        }
    }

    /**
     * Atomically publishes a validated replacement received from the catalog
     * import job. Readers see either the old catalog or the complete new one.
     */
    public void replaceCatalog(Map<String, ProductAvailability> replacementProducts) {
        Map<String, ProductAvailability> validatedProducts = validatedCopy(replacementProducts);
        ReentrantReadWriteLock.WriteLock writeLock = catalogLock.writeLock();
        writeLock.lock();
        try {
            products.clear();
            products.putAll(validatedProducts);
        } finally {
            writeLock.unlock();
        }
    }

    public void updateAvailability(ProductAvailability availability) {
        Objects.requireNonNull(availability, "Availability cannot be null.");
        ReentrantReadWriteLock.WriteLock writeLock = catalogLock.writeLock();
        writeLock.lock();
        try {
            products.put(availability.sku(), availability);
        } finally {
            writeLock.unlock();
        }
    }

    public boolean isFairLock() {
        return catalogLock.isFair();
    }

    private static Map<String, ProductAvailability> validatedCopy(
            Map<String, ProductAvailability> replacementProducts
    ) {
        Objects.requireNonNull(replacementProducts, "Replacement products cannot be null.");
        Map<String, ProductAvailability> copy = new LinkedHashMap<>();
        for (Map.Entry<String, ProductAvailability> entry : replacementProducts.entrySet()) {
            String sku = entry.getKey();
            ProductAvailability availability = entry.getValue();
            validateSku(sku);
            Objects.requireNonNull(availability, "Product availability cannot be null.");
            if (!sku.equals(availability.sku())) {
                throw new IllegalArgumentException("Map key must match the product SKU.");
            }
            copy.put(sku, availability);
        }
        return copy;
    }

    private static void validateSku(String sku) {
        if (sku == null || sku.isBlank()) {
            throw new IllegalArgumentException("SKU cannot be blank.");
        }
    }
}
