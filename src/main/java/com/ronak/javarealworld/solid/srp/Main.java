package com.ronak.javarealworld.solid.srp;

import java.math.BigDecimal;

public final class Main {
    private Main() { }

    public static void main(String[] args) {
        var service = new InvoiceProcessingService(new InMemoryInvoiceRepository(), new InvoiceTotalCalculator(),
                new InvoiceEmailSender());
        service.process(new Invoice("INV-2026-1042", "billing@acme.example", new BigDecimal("12500.00")));
    }
}
