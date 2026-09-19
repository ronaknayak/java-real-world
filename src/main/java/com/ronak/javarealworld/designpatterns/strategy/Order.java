package com.ronak.javarealworld.designpatterns.strategy;

import java.math.BigDecimal;

public record Order(String orderNumber, CustomerTier customerTier, BigDecimal merchandiseTotal) {
    public Order {
        if (merchandiseTotal.signum() < 0) {
            throw new IllegalArgumentException("Merchandise total cannot be negative");
        }
    }
}
