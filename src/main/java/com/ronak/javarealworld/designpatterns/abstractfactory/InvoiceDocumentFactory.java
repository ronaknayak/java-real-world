package com.ronak.javarealworld.designpatterns.abstractfactory;

/** Creates a compatible family of regional invoice-document components. */
public interface InvoiceDocumentFactory {
    InvoiceNumberGenerator createNumberGenerator();

    InvoiceFooterProvider createFooterProvider();
}