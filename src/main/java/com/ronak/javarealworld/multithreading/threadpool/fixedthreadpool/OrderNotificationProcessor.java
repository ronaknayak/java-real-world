package com.ronak.javarealworld.multithreading.threadpool.fixedthreadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public final class OrderNotificationProcessor implements AutoCloseable {

    private final ExecutorService executor;

    public OrderNotificationProcessor(int workerCount) {
        if (workerCount <= 0) {
            throw new IllegalArgumentException("Worker count must be positive.");
        }

        ThreadFactory threadFactory = new NamedThreadFactory(
                "order-notification-worker"
        );

        this.executor = Executors.newFixedThreadPool(
                workerCount,
                threadFactory
        );
    }

    public void submit(OrderNotification notification) {
        if (notification == null) {
            throw new IllegalArgumentException("Notification cannot be null.");
        }

        executor.submit(() -> process(notification));
    }

    private void process(OrderNotification notification) {
        try {
            System.out.printf(
                    "[%s] Processing notification for order=%s, customer=%s%n",
                    Thread.currentThread().getName(),
                    notification.orderId(),
                    notification.customerId()
            );

            sendNotification(notification);

            System.out.printf(
                    "[%s] Notification successfully processed for order=%s%n",
                    Thread.currentThread().getName(),
                    notification.orderId()
            );

        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();

            System.err.printf(
                    Thread.currentThread().getName(),
                    "[%s] Notification processing interrupted for order=%s%n",
                    notification.orderId()
            );

        } catch (Exception exception) {
            System.err.printf(
                    "[%s] Failed to process notification for order=%s: %s%n",
                    Thread.currentThread().getName(),
                    notification.orderId(),
                    exception.getMessage()
            );
        }
    }

    private void sendNotification(OrderNotification notification)
            throws InterruptedException {

        // Simulate external I/O such as email/SMS/push notification.
        Thread.sleep(1_000);

        System.out.printf(
                "[%s] Notification sent: %s%n",
                Thread.currentThread().getName(),
                notification.message()
        );
    }

    @Override
    public void close() {
        executor.shutdown();

        System.out.println("Order notification processor shutdown initiated.");
    }

    private static final class NamedThreadFactory implements ThreadFactory {

        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String threadNamePrefix;

        private NamedThreadFactory(String threadNamePrefix) {
            this.threadNamePrefix = threadNamePrefix;
        }

        @Override
        public Thread newThread(Runnable task) {
            Thread thread = new Thread(
                    task,
                    threadNamePrefix + "-" + threadNumber.getAndIncrement()
            );

            thread.setUncaughtExceptionHandler((threadObject, exception) ->
                    System.err.printf(
                            "[%s] Uncaught exception: %s%n",
                            threadObject.getName(),
                            exception.getMessage()
                    )
            );

            return thread;
        }
    }
}