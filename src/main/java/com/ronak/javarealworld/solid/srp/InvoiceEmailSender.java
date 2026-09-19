package com.ronak.javarealworld.solid.srp;

import java.math.BigDecimal;

public final class InvoiceEmailSender {
    public void send(Invoice invoice, BigDecimal total) {
        System.out.printf("Invoice %s sent to %s. Total: %s%n",
                invoice.invoiceNumber(), invoice.customerEmail(), total);
    }
}
