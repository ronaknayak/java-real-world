package com.ronak.javarealworld.solid.srp;

import java.util.HashMap;
import java.util.Map;

public final class InMemoryInvoiceRepository implements InvoiceRepository {
    private final Map<String, Invoice> invoices = new HashMap<>();

    @Override
    public void save(Invoice invoice) {
        invoices.put(invoice.invoiceNumber(), invoice);
    }
}
