package com.ronak.javarealworld.multithreading.visibility;

/**
 * Demonstrates using volatile for a cross-thread shutdown signal.
 */
public final class Main {

    private Main() {
    }

    public static void main(String[] args) throws InterruptedException {
        PollingWorker worker = new PollingWorker();
        Thread pollingThread = new Thread(worker::pollUntilShutdown, "fulfillment-polling-worker");

        pollingThread.start();
        worker.awaitStart();

        System.out.println("Requesting fulfillment polling shutdown.");
        worker.requestShutdown();
        pollingThread.join();

        System.out.println("Worker observed shutdown: " + worker.isShutdownRequested());
    }
}
