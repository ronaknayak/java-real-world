package com.ronak.javarealworld.solid.lsp;

public final class EmailNotificationChannel implements NotificationChannel {
    @Override
    public DeliveryReceipt deliver(Notification notification) {
        System.out.println("Email queued for " + notification.recipient());
        return new DeliveryReceipt("email", notification.recipient(), true);
    }
}
