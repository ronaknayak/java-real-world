package com.ronak.javarealworld.java8.defaultmethod;

/** Another provider that automatically receives the shared default behavior. */
public final class SmsNotificationChannel implements NotificationChannel {
    @Override
    public void send(Notification notification) throws NotificationDeliveryException {
        if (notification.recipient().isBlank()) {
            throw new NotificationDeliveryException("SMS recipient is invalid");
        }
    }
}