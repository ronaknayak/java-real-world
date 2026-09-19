package com.ronak.javarealworld.solid.srp;

import java.math.BigDecimal;

public record Invoice(String invoiceNumber, String customerEmail, BigDecimal subtotal) {
    public Invoice {
        if (subtotal.signum() < 0) {
            throw new IllegalArgumentException("Subtotal cannot be negative");
        }
    }
}
