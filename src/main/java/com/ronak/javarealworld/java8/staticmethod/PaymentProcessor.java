package com.ronak.javarealworld.java8.staticmethod;

import java.math.BigDecimal;
import java.util.Locale;
import java.util.Objects;

/** Contract for payment implementations plus helpers shared by the contract. */
public interface PaymentProcessor {
    PaymentReceipt process(PaymentRequest request);

    /** Interface static methods are called through PaymentProcessor, never an instance. */
    static PaymentMethod parseMethod(String value) {
        Objects.requireNonNull(value, "payment method must not be null");
        try {
            return PaymentMethod.valueOf(value.trim().toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException("Unsupported payment method: " + value, exception);
        }
    }

    /** Shared contract validation belongs to the processor contract itself. */
    static void validateRequest(PaymentRequest request) {
        Objects.requireNonNull(request, "request must not be null");
        if (request.amount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("payment amount must be positive");
        }
    }
}