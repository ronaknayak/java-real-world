package com.ronak.javarealworld.designpatterns.abstractfactory;

/** Generates invoice identifiers according to a regional document convention. */
@FunctionalInterface
public interface InvoiceNumberGenerator {
    String nextNumber();
}