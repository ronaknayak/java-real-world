package com.ronak.javarealworld.multithreading.lockapi.reentrant;

import java.time.Duration;

/**
 * Demonstrates the blocking, non-blocking, and timed operations of a
 * ReentrantLock-backed dispatch queue.
 */
public final class Main {

    private Main() {
    }

    public static void main(String[] args) throws InterruptedException {
        DispatchQueue dispatchQueue = new DispatchQueue(2);
        dispatchQueue.submit("ORD-1001");
        dispatchQueue.submit("ORD-1002");

        boolean submittedImmediately = dispatchQueue.trySubmit("ORD-1003");
        System.out.println("Immediate submission accepted: " + submittedImmediately);

        Thread fulfillmentWorker = new Thread(() -> take(dispatchQueue), "fulfillment-worker");
        fulfillmentWorker.start();
        fulfillmentWorker.join();

        boolean submittedWithinTimeout = dispatchQueue.trySubmit("ORD-1004", Duration.ofSeconds(1));
        System.out.println("Timed submission accepted: " + submittedWithinTimeout);
        System.out.println("Queue metrics: " + dispatchQueue.metrics());
        System.out.println("Pending dispatches: " + dispatchQueue.pendingDispatches());
    }

    private static void take(DispatchQueue dispatchQueue) {
        try {
            System.out.println("Dispatching " + dispatchQueue.take());
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
            System.out.println("Fulfillment worker interrupted during shutdown.");
        }
    }
}
