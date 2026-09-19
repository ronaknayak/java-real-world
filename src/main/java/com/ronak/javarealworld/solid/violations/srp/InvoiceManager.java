package com.ronak.javarealworld.solid.violations.srp;

import java.math.BigDecimal;

/** Deliberately violates SRP: billing policy, storage, and notification are coupled. */
public final class InvoiceManager {
    public void process(String invoiceNumber, String customerEmail, BigDecimal subtotal) {
        BigDecimal total = subtotal.multiply(new BigDecimal("1.18"));
        System.out.println("INSERT INTO invoices VALUES ('" + invoiceNumber + "', " + total + ")");
        System.out.println("Sending invoice " + invoiceNumber + " to " + customerEmail);
    }
}
