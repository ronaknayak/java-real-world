package com.ronak.javarealworld.multithreading.synchronizedd;

/**
 * Keeps an item's available stock consistent when several order-processing threads
 * attempt reservations at the same time.
 */
public final class Inventory {

    private int availableUnits;

    public Inventory(int availableUnits) {
        if (availableUnits < 0) {
            throw new IllegalArgumentException("Available units cannot be negative.");
        }
        this.availableUnits = availableUnits;
    }

    /**
     * Atomically checks and reduces stock. The instance monitor prevents two
     * threads from reading the same stock before either one updates it.
     */
    public synchronized boolean reserve(int requestedUnits) {
        if (requestedUnits <= 0) {
            throw new IllegalArgumentException("Requested units must be positive.");
        }

        if (requestedUnits > availableUnits) {
            return false;
        }

        availableUnits -= requestedUnits;
        return true;
    }

    public synchronized int availableUnits() {
        return availableUnits;
    }
}
