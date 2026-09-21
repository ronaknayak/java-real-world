package com.ronak.javarealworld.multithreading.lockapi.stampedlock;

import java.time.Duration;
import java.time.Instant;

/**
 * Demonstrates optimistic location reads and exclusive driver updates.
 */
public final class Main {

    private Main() {
    }

    public static void main(String[] args) throws InterruptedException {
        DeliveryLocationTracker tracker = new DeliveryLocationTracker(
                new DeliveryLocation(19.0760, 72.8777, Instant.now().minus(Duration.ofMinutes(10)))
        );

        Thread firstReader = new Thread(() -> printLocation(tracker), "tracking-reader-1");
        Thread secondReader = new Thread(() -> printLocation(tracker), "tracking-reader-2");
        firstReader.start();
        secondReader.start();
        firstReader.join();
        secondReader.join();

        boolean refreshed = tracker.refreshIfStale(
                new DeliveryLocation(19.0896, 72.8656, Instant.now()),
                Duration.ofMinutes(2)
        );
        System.out.println("Stale location refreshed: " + refreshed);
        System.out.println("Latest location: " + tracker.latestLocation());
    }

    private static void printLocation(DeliveryLocationTracker tracker) {
        System.out.println(Thread.currentThread().getName() + " read " + tracker.latestLocation());
    }
}
