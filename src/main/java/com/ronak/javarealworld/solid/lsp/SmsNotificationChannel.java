package com.ronak.javarealworld.solid.lsp;

public final class SmsNotificationChannel implements NotificationChannel {
    @Override
    public DeliveryReceipt deliver(Notification notification) {
        System.out.println("SMS queued for " + notification.recipient());
        return new DeliveryReceipt("sms", notification.recipient(), true);
    }
}
