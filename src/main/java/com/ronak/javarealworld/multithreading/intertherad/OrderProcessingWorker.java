package com.ronak.javarealworld.multithreading.intertherad;

import java.util.ArrayDeque;
import java.util.Queue;

public final class OrderProcessingWorker implements Runnable {

    private final Object lock = new Object();
    private final Queue<String> orderQueue = new ArrayDeque<>();

    private boolean shutdownRequested;
    private final Thread workerThread;

    public OrderProcessingWorker() {
        this.workerThread = new Thread(this, "order-processing-worker");
    }

    public void start() {
        workerThread.start();
    }

    public void submitOrder(String orderId) {
        if (orderId == null || orderId.isBlank()) {
            throw new IllegalArgumentException("Order ID must not be blank.");
        }

        synchronized (lock) {
            if (shutdownRequested) {
                throw new IllegalStateException(
                        "Cannot submit order. Worker is shutting down.");
            }

            orderQueue.offer(orderId);

            System.out.println("order submitted");

            // Wake the worker because new work is available.
            lock.notifyAll();
        }
    }

    public void shutdown() throws InterruptedException {
        synchronized (lock) {
            shutdownRequested = true;

            // Wake the worker so it can detect shutdown immediately.
            lock.notifyAll();
        }

        workerThread.join();
    }

    @Override
    public void run() {
        while (true) {
            String orderId;

            synchronized (lock) {
                /*
                 * Wait while there is no work and shutdown was not requested.
                 */
                while (orderQueue.isEmpty() && !shutdownRequested) {
                    try {
                        // Immediately releases Lock of the object and entered into waiting state.
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }

                /*
                 * If shutdown was requested and no work remains,
                 * terminate the worker.
                 */
                if (shutdownRequested && orderQueue.isEmpty()) {
                    return;
                }

                /*
                 * Remove one order from the queue.
                 */
                orderId = orderQueue.poll();
            }

            /*
             * Process outside synchronized block.
             *
             * This is important: we don't hold the lock while doing
             * potentially slow business processing.
             */
            processOrder(orderId);
        }
    }

    private void processOrder(String orderId) {
        System.out.printf(
                "[%s] Processing order: %s%n",
                Thread.currentThread().getName(),
                orderId
        );

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.printf(
                "[%s] Completed order: %s%n",
                Thread.currentThread().getName(),
                orderId
        );
    }
}