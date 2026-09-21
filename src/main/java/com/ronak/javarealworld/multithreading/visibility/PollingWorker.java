package com.ronak.javarealworld.multithreading.visibility;

import java.util.concurrent.CountDownLatch;

/**
 * Simulates a worker that polls a fulfillment partner until the application
 * requests a graceful shutdown.
 */
public final class PollingWorker {

    private volatile boolean shutdownRequested;
    private final CountDownLatch started = new CountDownLatch(1);

    /**
     * Repeatedly checks the volatile flag. Write by another thread becomes
     * visible to this worker without acquiring a monitor.
     */
    public void pollUntilShutdown() {
        started.countDown();
        while (!shutdownRequested) {
            System.out.println("Stated polling");
        }
    }

    public void requestShutdown() {
        shutdownRequested = true;
    }

    public boolean isShutdownRequested() {
        return shutdownRequested;
    }

    public void awaitStart() throws InterruptedException {
        started.await();
    }
}
