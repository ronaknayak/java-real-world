package com.ronak.javarealworld.designpatterns.factory;

import java.math.BigDecimal;

public record PaymentRequest(String orderNumber, BigDecimal amount, PaymentMethod paymentMethod) {
    public PaymentRequest {
        if (amount.signum() <= 0) {
            throw new IllegalArgumentException("Payment amount must be positive");
        }
    }
}
