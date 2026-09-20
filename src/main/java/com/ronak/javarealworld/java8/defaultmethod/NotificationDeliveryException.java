package com.ronak.javarealworld.java8.defaultmethod;

/** Checked failure from a notification provider. */
public class NotificationDeliveryException extends Exception {
    public NotificationDeliveryException(String message) {
        super(message);
    }
}