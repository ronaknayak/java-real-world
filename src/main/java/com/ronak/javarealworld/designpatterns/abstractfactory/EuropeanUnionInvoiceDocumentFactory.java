package com.ronak.javarealworld.designpatterns.abstractfactory;

import java.util.concurrent.atomic.AtomicInteger;

/** European Union invoice components kept together as one compatible family. */
public final class EuropeanUnionInvoiceDocumentFactory implements InvoiceDocumentFactory {
    @Override
    public InvoiceNumberGenerator createNumberGenerator() {
        AtomicInteger sequence = new AtomicInteger(5000);
        return () -> "EU-VAT-" + sequence.incrementAndGet();
    }

    @Override
    public InvoiceFooterProvider createFooterProvider() {
        return () -> "VAT reverse-charge wording is included where applicable.";
    }
}