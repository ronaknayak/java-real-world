package com.ronak.javarealworld.java8.defaultmethod;

import java.util.Objects;

/**
 * Provider contract. Default methods add shared behavior without requiring
 * every existing provider implementation to repeat it.
 */
public interface NotificationChannel {
    void send(Notification notification) throws NotificationDeliveryException;

    /** Shared audit workflow added after providers already existed. */
    default DeliveryReceipt sendWithAudit(Notification notification, AuditTrail auditTrail)
            throws NotificationDeliveryException {
        Objects.requireNonNull(notification, "notification must not be null");
        Objects.requireNonNull(auditTrail, "auditTrail must not be null");
        send(notification);
        auditTrail.record(notification.id(), channelName());
        return new DeliveryReceipt(notification.id(), channelName());
    }

    /** Shared fallback naming avoids adding boilerplate to every provider. */
    default String channelName() {
        return getClass().getSimpleName();
    }
}