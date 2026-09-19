package com.ronak.javarealworld.designpatterns.strategy;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class EnterpriseContractDiscountStrategy implements DiscountStrategy {
    private static final BigDecimal DISCOUNT_RATE = new BigDecimal("0.15");
    private static final BigDecimal MAX_DISCOUNT = new BigDecimal("50000.00");

    @Override
    public BigDecimal calculateDiscount(Order order) {
        return order.merchandiseTotal()
                .multiply(DISCOUNT_RATE)
                .min(MAX_DISCOUNT)
                .setScale(2, RoundingMode.HALF_UP);
    }
}
