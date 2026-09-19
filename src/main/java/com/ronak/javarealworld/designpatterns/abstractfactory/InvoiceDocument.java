package com.ronak.javarealworld.designpatterns.abstractfactory;

import java.math.BigDecimal;

/** Render-ready invoice assembled from regional document components. */
public record InvoiceDocument(String invoiceNumber, String customerName, BigDecimal total, String footer) { }