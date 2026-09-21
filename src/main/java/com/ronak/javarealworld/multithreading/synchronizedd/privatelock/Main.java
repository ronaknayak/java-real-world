package com.ronak.javarealworld.multithreading.synchronizedd.privatelock;

/**
 * Demonstrates protecting mutable state with a private lock object.
 */
public final class Main {

    private Main() {
    }

    public static void main(String[] args) throws InterruptedException {
        DispatchQueue dispatchQueue = new DispatchQueue();

        Thread firstOrderWorker = new Thread(
                () -> submitDispatches(dispatchQueue, "ORD-1001", "ORD-1002"),
                "order-worker-1"
        );
        Thread secondOrderWorker = new Thread(
                () -> submitDispatches(dispatchQueue, "ORD-1003", "ORD-1004"),
                "order-worker-2"
        );

        firstOrderWorker.start();
        secondOrderWorker.start();
        firstOrderWorker.join();
        secondOrderWorker.join();

        System.out.println("Pending dispatches: " + dispatchQueue.pendingDispatchCount());
        dispatchQueue.poll().ifPresent(dispatchId -> System.out.println("Dispatching " + dispatchId));
    }

    private static void submitDispatches(DispatchQueue dispatchQueue, String... dispatchIds) {
        for (String dispatchId : dispatchIds) {
            dispatchQueue.enqueue(dispatchId);
        }
    }
}
