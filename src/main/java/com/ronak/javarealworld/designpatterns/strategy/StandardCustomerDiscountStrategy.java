package com.ronak.javarealworld.designpatterns.strategy;

import java.math.BigDecimal;

public final class StandardCustomerDiscountStrategy implements DiscountStrategy {
    @Override
    public BigDecimal calculateDiscount(Order order) {
        return BigDecimal.ZERO;
    }
}
