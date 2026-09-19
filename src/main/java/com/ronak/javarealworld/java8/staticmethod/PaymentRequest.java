package com.ronak.javarealworld.java8.staticmethod;

import java.math.BigDecimal;
import java.util.Objects;

public record PaymentRequest(String reference, BigDecimal amount) {
    public PaymentRequest {
        Objects.requireNonNull(reference, "reference must not be null");
        Objects.requireNonNull(amount, "amount must not be null");
        if (reference.isBlank()) throw new IllegalArgumentException("reference must not be blank");
    }
}