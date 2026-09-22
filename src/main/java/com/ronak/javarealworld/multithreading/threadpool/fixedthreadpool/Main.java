package com.ronak.javarealworld.multithreading.threadpool.fixedthreadpool;

public final class Main {

    private Main() {
    }

    public static void main(String[] args) throws InterruptedException {

        try (OrderNotificationProcessor processor =
                     new OrderNotificationProcessor(3)) {

            for (int i = 1; i <= 10; i++) {

                OrderNotification notification =
                        new OrderNotification(
                                "ORD-%04d".formatted(i),
                                "CUST-%04d".formatted(i),
                                "Your order has been shipped."
                        );

                processor.submit(notification);
            }

            Thread.sleep(4_000);
        }
    }
}