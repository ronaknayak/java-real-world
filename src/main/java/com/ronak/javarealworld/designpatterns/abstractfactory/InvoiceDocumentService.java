package com.ronak.javarealworld.designpatterns.abstractfactory;

import java.util.Objects;

/** Application workflow that depends only on the abstract product family. */
public final class InvoiceDocumentService {
    private final InvoiceNumberGenerator numberGenerator;
    private final InvoiceFooterProvider footerProvider;

    public InvoiceDocumentService(InvoiceDocumentFactory factory) {
        Objects.requireNonNull(factory, "factory must not be null");
        this.numberGenerator = factory.createNumberGenerator();
        this.footerProvider = factory.createFooterProvider();
    }

    public InvoiceDocument createInvoice(InvoiceRequest request) {
        Objects.requireNonNull(request, "request must not be null");
        return new InvoiceDocument(
                numberGenerator.nextNumber(),
                request.customerName(),
                request.total(),
                footerProvider.footer());
    }
}