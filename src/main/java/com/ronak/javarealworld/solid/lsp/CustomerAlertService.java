package com.ronak.javarealworld.solid.lsp;

public final class CustomerAlertService {
    public DeliveryReceipt alert(NotificationChannel channel, Notification notification) {
        return channel.deliver(notification);
    }
}
