package com.ronak.javarealworld.multithreading.synchronizedd.privatelock;

import java.util.ArrayDeque;
import java.util.Optional;
import java.util.Queue;

/**
 * Stores dispatch requests submitted by several order-processing workers.
 */
public final class DispatchQueue {

    private final Object lock = new Object();
    private final Queue<String> dispatchIds = new ArrayDeque<>();

    /**
     * Uses an internal lock so callers cannot accidentally acquire or expose
     * the monitor that protects this queue's mutable state.
     */
    public void enqueue(String dispatchId) {
        if (dispatchId == null || dispatchId.isBlank()) {
            throw new IllegalArgumentException("Dispatch ID cannot be blank.");
        }

        synchronized (lock) {
            dispatchIds.add(dispatchId);
        }
    }

    public Optional<String> poll() {
        synchronized (lock) {
            return Optional.ofNullable(dispatchIds.poll());
        }
    }

    public int pendingDispatchCount() {
        synchronized (lock) {
            return dispatchIds.size();
        }
    }
}
