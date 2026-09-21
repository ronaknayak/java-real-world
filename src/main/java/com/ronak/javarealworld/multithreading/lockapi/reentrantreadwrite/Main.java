package com.ronak.javarealworld.multithreading.lockapi.reentrantreadwrite;

import java.util.Map;

/**
 * Demonstrates many readers sharing a catalog read lock and a refresh worker
 * taking the exclusive write lock.
 */
public final class Main {

    private Main() {
    }

    public static void main(String[] args) throws InterruptedException {
        ProductCatalog catalog = new ProductCatalog(Map.of(
                "LAPTOP-14", new ProductAvailability("LAPTOP-14", 12),
                "MOUSE-WL", new ProductAvailability("MOUSE-WL", 30)
        ));

        Thread firstReader = new Thread(() -> readAvailability(catalog, "LAPTOP-14"), "catalog-reader-1");
        Thread secondReader = new Thread(() -> readAvailability(catalog, "LAPTOP-14"), "catalog-reader-2");
        Thread thirdReader = new Thread(() -> readAvailability(catalog, "MOUSE-WL"), "catalog-reader-3");

        firstReader.start();
        secondReader.start();
        thirdReader.start();
        firstReader.join();
        secondReader.join();
        thirdReader.join();

        Thread catalogRefresh = new Thread(
                () -> catalog.updateAvailability(new ProductAvailability("LAPTOP-14", 9)),
                "catalog-refresh-worker"
        );
        catalogRefresh.start();
        catalogRefresh.join();

        System.out.println("Fair lock enabled: " + catalog.isFairLock());
        System.out.println("Catalog after refresh: " + catalog.snapshot());
    }

    private static void readAvailability(ProductCatalog catalog, String sku) {
        catalog.findBySku(sku).ifPresent(availability -> System.out.printf(
                "%s read %s: %d units%n",
                Thread.currentThread().getName(),
                availability.sku(),
                availability.availableUnits()
        ));
    }
}
