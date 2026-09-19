package com.ronak.javarealworld.designpatterns.abstractfactory;

import java.math.BigDecimal;

/** Run target for the Abstract Factory pattern example. */
public final class Main {
    private Main() { }

    public static void main(String[] args) {
        var indiaService = new InvoiceDocumentService(new IndiaInvoiceDocumentFactory());
        var europeanService = new InvoiceDocumentService(new EuropeanUnionInvoiceDocumentFactory());

        InvoiceRequest request = new InvoiceRequest("ORD-4821", "Northstar Retail", new BigDecimal("2499.00"));
        System.out.println(indiaService.createInvoice(request));
        System.out.println(europeanService.createInvoice(request));
    }
}