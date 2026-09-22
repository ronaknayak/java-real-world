package com.ronak.javarealworld.multithreading.threadpool.fixedthreadpool;

public record OrderNotification(
        String orderId,
        String customerId,
        String message
) {
    public OrderNotification {
        if (orderId == null || orderId.isBlank()) {
            throw new IllegalArgumentException("Order ID cannot be blank.");
        }

        if (customerId == null || customerId.isBlank()) {
            throw new IllegalArgumentException("Customer ID cannot be blank.");
        }

        if (message == null || message.isBlank()) {
            throw new IllegalArgumentException("Message cannot be blank.");
        }
    }
}