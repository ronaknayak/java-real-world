package com.ronak.javarealworld.designpatterns.abstractfactory;

import java.math.BigDecimal;
import java.util.Objects;

/** Input supplied by an order workflow when an invoice must be produced. */
public record InvoiceRequest(String orderId, String customerName, BigDecimal total) {
    public InvoiceRequest {
        Objects.requireNonNull(orderId, "orderId must not be null");
        Objects.requireNonNull(customerName, "customerName must not be null");
        Objects.requireNonNull(total, "total must not be null");
        if (total.signum() < 0) {
            throw new IllegalArgumentException("total must not be negative");
        }
    }
}