package com.ronak.javarealworld.multithreading.lockapi.reentrant;

import java.time.Duration;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * A bounded queue for handing completed orders from intake workers to a
 * fulfillment worker.
 */
public final class DispatchQueue {

    private final ReentrantLock lock = new ReentrantLock(true);
    private final Condition dispatchAvailable = lock.newCondition();
    private final Condition capacityAvailable = lock.newCondition();
    private final ArrayDeque<String> dispatchIds = new ArrayDeque<>();
    private final int capacity;

    public DispatchQueue(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive.");
        }
        this.capacity = capacity;
    }

    /**
     * Adds a dispatch request, waiting interruptibly until queue capacity is
     * available. The lock is always released, including when interrupted.
     */
    public void submit(String dispatchId) throws InterruptedException {
        System.out.println("DispatchQueue.submit");
        validateDispatchId(dispatchId);
        lock.lockInterruptibly();
        try {
            while (dispatchIds.size() == capacity) {
                capacityAvailable.await();
            }
            dispatchIds.addLast(dispatchId);
            dispatchAvailable.signal();
        } finally {
            lock.unlock();
        }
    }

    /**
     * Attempts to submit without waiting for either the lock or queue capacity.
     */
    public boolean trySubmit(String dispatchId) {
        System.out.println("DispatchQueue.trySubmit");
        validateDispatchId(dispatchId);
        if (!lock.tryLock()) {
            return false;
        }

        try {
            if (dispatchIds.size() == capacity) {
                return false;
            }
            dispatchIds.addLast(dispatchId);
            dispatchAvailable.signal();
            return true;
        } finally {
            lock.unlock();
        }
    }

    /**
     * Attempts to acquire the lock and obtain queue capacity before the supplied
     * timeout expires.
     */
    public boolean trySubmit(String dispatchId, Duration timeout) throws InterruptedException {
        System.out.println("DispatchQueue.trySubmit with timeout");
        validateDispatchId(dispatchId);
        Objects.requireNonNull(timeout, "Timeout cannot be null.");
        if (timeout.isNegative()) {
            throw new IllegalArgumentException("Timeout cannot be negative.");
        }

        long timeoutNanos = timeout.toNanos();
        long deadline = System.nanoTime() + timeoutNanos;
        if (!lock.tryLock(timeoutNanos, TimeUnit.NANOSECONDS)) {
            return false;
        }

        try {
            long remainingNanos = deadline - System.nanoTime();
            while (dispatchIds.size() == capacity) {
                if (remainingNanos <= 0) {
                    return false;
                }
                remainingNanos = capacityAvailable.awaitNanos(remainingNanos);
            }
            dispatchIds.addLast(dispatchId);
            dispatchAvailable.signal();
            return true;
        } finally {
            lock.unlock();
        }
    }

    /**
     * Retrieves the next request, waiting interruptibly until a request exists.
     */
    public String take() throws InterruptedException {
        System.out.println("DispatchQueue.take");
        lock.lockInterruptibly();
        try {
            while (dispatchIds.isEmpty()) {
                dispatchAvailable.await();
            }
            String dispatchId = dispatchIds.removeFirst();
            capacityAvailable.signal();
            return dispatchId;
        } finally {
            lock.unlock();
        }
    }

    public QueueMetrics metrics() {
        lock.lock();
        try {
            return new QueueMetrics(dispatchIds.size(), capacity, lock.isFair(), lock.getQueueLength());
        } finally {
            lock.unlock();
        }
    }

    public List<String> pendingDispatches() {
        lock.lock();
        try {
            return List.copyOf(dispatchIds);
        } finally {
            lock.unlock();
        }
    }

    private static void validateDispatchId(String dispatchId) {
        if (dispatchId == null || dispatchId.isBlank()) {
            throw new IllegalArgumentException("Dispatch ID cannot be blank.");
        }
    }

    public record QueueMetrics(int pendingDispatches, int capacity, boolean fairLock, int waitingThreads) {
    }
}
