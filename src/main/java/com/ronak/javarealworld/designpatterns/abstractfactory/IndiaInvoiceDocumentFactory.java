package com.ronak.javarealworld.designpatterns.abstractfactory;

import java.util.concurrent.atomic.AtomicInteger;

/** Indian invoice components kept together as one compatible family. */
public final class IndiaInvoiceDocumentFactory implements InvoiceDocumentFactory {
    @Override
    public InvoiceNumberGenerator createNumberGenerator() {
        AtomicInteger sequence = new AtomicInteger(1000);
        return () -> "IN-GST-" + sequence.incrementAndGet();
    }

    @Override
    public InvoiceFooterProvider createFooterProvider() {
        return () -> "GSTIN and place-of-supply details are shown on the tax invoice.";
    }
}