package com.ronak.javarealworld.java8.defaultmethod;

import java.util.List;

/** Service that uses the default method shared by all channels. */
public final class NotificationService {
    private final AuditTrail auditTrail;

    public NotificationService(AuditTrail auditTrail) {
        this.auditTrail = auditTrail;
    }

    public List<DeliveryReceipt> sendToAll(Notification notification, List<NotificationChannel> channels) {
        return channels.stream()
                .map(channel -> send(channel, notification))
                .toList();
    }

    private DeliveryReceipt send(NotificationChannel channel, Notification notification) {
        try {
            return channel.sendWithAudit(notification, auditTrail);
        } catch (NotificationDeliveryException exception) {
            throw new NotificationRuntimeException(exception);
        }
    }

    private static final class NotificationRuntimeException extends RuntimeException {
        private NotificationRuntimeException(NotificationDeliveryException cause) {
            super(cause);
        }
    }
}