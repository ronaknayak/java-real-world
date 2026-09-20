package com.ronak.javarealworld.java8.defaultmethod;

/** Existing provider that only implements the original send capability. */
public final class EmailNotificationChannel implements NotificationChannel {
    @Override
    public void send(Notification notification) throws NotificationDeliveryException {
        if (!notification.recipient().contains("@")) {
            throw new NotificationDeliveryException("email recipient is invalid");
        }
    }
}