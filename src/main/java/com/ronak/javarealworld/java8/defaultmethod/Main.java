package com.ronak.javarealworld.java8.defaultmethod;

import java.util.List;

/** Run target for the default-method example. */
public final class Main {
    private Main() { }

    public static void main(String[] args) throws NotificationDeliveryException {
        var auditTrail = new AuditTrail();
        var service = new NotificationService(auditTrail);
        var notification = new Notification("N-100", "customer@example.test", "Your order shipped.");

        var receipts = service.sendToAll(notification, List.of(
                new EmailNotificationChannel(),
                new SmsNotificationChannel()));

        System.out.println("Receipts: " + receipts);
        System.out.println("Audit events: " + auditTrail.events());
    }
}