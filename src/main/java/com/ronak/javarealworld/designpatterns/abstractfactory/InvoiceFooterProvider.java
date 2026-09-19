package com.ronak.javarealworld.designpatterns.abstractfactory;

/** Supplies the compliance footer associated with a regional invoice family. */
@FunctionalInterface
public interface InvoiceFooterProvider {
    String footer();
}