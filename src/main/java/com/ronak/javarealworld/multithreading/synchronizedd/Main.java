package com.ronak.javarealworld.multithreading.synchronizedd;

/**
 * Demonstrates synchronized access to shared inventory during order processing.
 */
public final class Main {

    private Main() {
    }

    public static void main(String[] args) throws InterruptedException {
        Inventory inventory = new Inventory(8);

        Thread firstOrder = new Thread(() -> reserve(inventory, "Order-1001", 5), "order-worker-1");
        Thread secondOrder = new Thread(() -> reserve(inventory, "Order-1002", 4), "order-worker-2");

        firstOrder.start();
        secondOrder.start();

        firstOrder.join();
        secondOrder.join();

        System.out.println("Remaining stock: " + inventory.getAvailableUnits());
    }

    private static void reserve(Inventory inventory, String orderId, int units) {
        boolean reserved = inventory.reserve(units);
        String result = reserved ? "reserved" : "rejected because stock is insufficient";
        System.out.printf("%s: %s%n", orderId, result);
    }
}
