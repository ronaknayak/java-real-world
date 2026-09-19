package com.ronak.javarealworld.designpatterns.strategy;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class GoldCustomerDiscountStrategy implements DiscountStrategy {
    private static final BigDecimal DISCOUNT_RATE = new BigDecimal("0.10");

    @Override
    public BigDecimal calculateDiscount(Order order) {
        return order.merchandiseTotal().multiply(DISCOUNT_RATE).setScale(2, RoundingMode.HALF_UP);
    }
}
