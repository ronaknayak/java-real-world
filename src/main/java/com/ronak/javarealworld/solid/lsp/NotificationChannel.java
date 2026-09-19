package com.ronak.javarealworld.solid.lsp;

public interface NotificationChannel {
    DeliveryReceipt deliver(Notification notification);
}
